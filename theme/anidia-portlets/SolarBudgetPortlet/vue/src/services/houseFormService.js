import { httpSolar } from './index'

const houseFormService = {
  /**
   * Request new example
   * API POST Method
   * @param { string } payload
   */
  postHouseForm: function(payload) {
    return httpSolar.post('solar-budgets', payload)
  },

}

export default houseFormService
