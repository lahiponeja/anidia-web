import { http } from './index'

const contactInfoService = {
  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postLeads: function(payload) {
    return http.post('leads', payload)
  },

}

export default contactInfoService
