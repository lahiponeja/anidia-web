import { http } from './http/index'
import qs from 'qs'

const houseFormService = {
  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postHouseForm: function(payload) {
    return http.post('gas-budgets', payload)
  },

}

export default houseFormService
