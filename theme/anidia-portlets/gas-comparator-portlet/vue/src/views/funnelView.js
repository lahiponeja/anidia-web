import funnelForm from '../components/funnel/funnelForm'

const funnelView = {
  components: {
    'funnel-form': funnelForm,
  },
  template: /*html*/`
    <div class="w-full bg-white pt-xxxl pb-xxxl">
      <funnel-form />
    </div>
  `
}

export default funnelView