import axios from 'axios'

export const http = axios.create({
  baseURL: '/o/sample-vue/v1.0/',
  headers: {
    'Access-Control-Allow-Origin': '*'
  }
})