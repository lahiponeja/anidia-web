import phonePrefixesArr from '../enums/phonePrefixes'

export default {
  data() {
    return {
      phonePrefix: "+34",
      phoneNumber: "",
      phonePrefixesOptions: phonePrefixesArr,
      activeFlag: "",
    }
  },

  computed: {
    fullPhoneNumber() {
      return `${this.phonePrefix} ${this.phoneNumber}`
    },
  },

  methods: {
    setFlag(e) {
      const { value } = e.target
      this.activeFlag = phonePrefixesArr.find((prefix) => { return prefix.value == value }).flagUrl
      console.log("this.activeFlag", this.activeFlag);
    },

    setPrefix(prefixNum, flagImgUrl) {
      this.phonePrefix = prefixNum
      this.activeFlag = flagImgUrl
    }
  },

  mounted() {
    this.setPrefix(phonePrefixesArr[0].value, phonePrefixesArr[0].flagUrl)
  }
}