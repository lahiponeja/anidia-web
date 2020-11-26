import phonePrefixesArr from '../enums/phonePrefixes'

export default {
  data() {
    return {
      phonePrefix: "+34",
      phoneNumber: "",
      phonePrefixesOptions: phonePrefixesArr,
    }
  },

  computed: {
    fullPhoneNumber() {
      return `${this.phonePrefix} ${this.phoneNumber}`
    }
  },
}