import { http } from './index'

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
