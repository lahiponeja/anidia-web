/*
Definition by DevOps TCK Team
Maintainer: javier.coscolla@the-cocktail.com
Required plugins:
· https://plugins.jenkins.io/azure-credentials/
*/

def defineAcr() {
  acRegistry="${env.BRANCH_NAME}"
  if ( acRegistry == "master" ) {
    return "rdxacrpro.azurecr.io"
  }
  else {
    return "rdxacruat.azurecr.io"
  }
}

pipeline {
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
    withCredentials([azureServicePrincipal('liferay-sp-credentials')])
  }

  agent { label 'jenkins-agent' }

  environment {
    PROJECT = 'anidia-web'
    COMMIT  = sh ( script: 'git rev-parse --short=7 HEAD', returnStdout: true).trim()
    AUTHOR  = sh ( script: 'git --no-pager show -s --format="%an" ${commit}', returnStdout: true).trim()

    // Container
    REGISTRY     = defineAcr()
    REGISTRY_URL = "https://${env.REGISTRY}"
    TAG          = "${env.COMMIT}"

    // Slack
    SLACK_COLOR_INFO  = '#6ECADC'
    SLACK_COLOR_GOOD  = '#3EB991'
    SLACK_COLOR_UGLY  = '#FFC300'
    SLACK_COLOR_BAD   = '#E01563'
    SLACK_CHANNEL     = '#anidia-web'

  }
  stages {
    stage('Notificacion') {
      steps {
        slackSend (
          color: "${env.SLACK_COLOR_INFO}",
          channel: "${env.SLACK_CHANNEL}",
          message: """
            *STARTED* Job <${env.BUILD_URL} | ${env.JOB_NAME}>.
            Branch ${env.BRANCH_NAME} Commit ${env.COMMIT}.
            Build ${env.BUILD_DISPLAY_NAME} by ${env.AUTHOR}.
          """
        )
      }
    }

    stage('Show Env') { steps { sh 'env|sort' } }

    stage('Login into Azure') {
      steps {
        sh """
          az login --service-principal -u ${env.AZURE_CLIENT_ID} -p ${env.AZURE_CLIENT_SECRET} --tenant ${env.AZURE_TENANT_ID}
          docker login -u ${env.AZURE_CLIENT_ID} -p ${env.AZURE_CLIENT_SECRET} ${env.REGISTRY_URL}
        """
      }
    }

    stage('Gradle v6 builds') {
      steps {
        sh """
          docker pull ${env.REGISTRY}/gradle6:latest
          docker tag ${env.REGISTRY}/gradle6:latest gradle6:latest
          docker build -f docker/gradle6.dockerfile . -t gradle6:builder
        """
      }
    }

    stage('Gradle v4 builds') {
      steps {
        sh """
          docker pull ${env.REGISTRY}/gradle4:latest
          docker tag ${env.REGISTRY}/gradle4:latest gradle4:latest
          docker build -f docker/gradle4.dockerfile . -t gradle4:builder
        """
      }
    }

  } // END OF STAGES

  post { // Post Actions
    always {
      cleanWs()
    }
    aborted {
      echo "Sending message to Slack"
      slackSend (
        color: "${env.SLACK_COLOR_UGLY}",
        channel: "${env.SLACK_CHANNEL}",
        message: """
          *ABORTED * Job <${env.BUILD_URL} | ${env.JOB_NAME}>.
          Branch ${env.BRANCH_NAME} Commit ${env.COMMIT}.
          Build ${env.BUILD_DISPLAY_NAME} by ${env.AUTHOR}.
        """
      )
    }
    failure {
      echo "Sending message to Slack"
      slackSend (
        color: "${env.SLACK_COLOR_BAD}",
        channel: "${env.SLACK_CHANNEL}",
        message: """
          *FAILED* Job <${env.BUILD_URL} | ${env.JOB_NAME}>.
          Branch: ${env.BRANCH_NAME} Commit: ${env.COMMIT}.
          Build ${env.BUILD_DISPLAY_NAME} by ${env.AUTHOR}.
        """
      )
    }
    success {
      echo "Sending message to Slack"
      slackSend (
        color: "${env.SLACK_COLOR_GOOD}",
        channel: "${env.SLACK_CHANNEL}",
        message: """
          *SUCCESS* Job <${env.BUILD_URL} | ${env.JOB_NAME}>.
          Branch: ${env.BRANCH_NAME} Commit: ${env.COMMIT}.
          Build ${env.BUILD_DISPLAY_NAME} by ${env.AUTHOR} took ${currentBuild.durationString}.
        """
      )
    }
  
  } // END OF POST ACTIONS

} // END OF PIPELINE
