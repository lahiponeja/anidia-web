import { httpSolar } from './index'

const contactInfoService = {
  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postLeads: function(payload) {
    return httpSolar.post('leads', payload)
  },

}

export default contactInfoService
