import privacyPolicyModal from './privacyPolicyModal'
import commercialModal from './commercialModal'

const modal = {
  inject: ["global"], // TODO: refactor once I tackle this business form.
  components: {
    privacyPolicyModal,
    commercialModal
  },
  computed: {
    // componentInstance() {
    //   const component = this.settings.componentName
      
    //   if (component) {
    //     return () => import(`../components/${component}`)
    //   }
    //   return undefined
    // },

    componentProps() {
      const props = this.settings.props

      return props
    },

    hasModalContent() {
      return this.settings.componentName || ''
    },

    settings() {
      return this.global.state.modalSettings
    },

    openModal() {
      return this.global.state.modalOpen ? { display: 'block' } : { display: 'none' }
    }
  },
  methods: {
    closeModal() {
      this.global.changeModalStatus({
        open: false,
        options: {
          type: '',
          componentName: ''
        }
      })
    }
  },
  template: /*html*/`
  <div class="an-modal" id="modal-privacy-accepted" :style="openModal">
    <div class="an-modal__content">
      <component
        :is="settings.componentName"
        v-if="hasModalContent"
        v-bind="{...componentProps}"
        @closeModal="closeModal"
      >
      </component>
    </div>
  </div>`
}

export default modal