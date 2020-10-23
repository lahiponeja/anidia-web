import axios from 'axios'

const locationOrigin = window.location.origin

export const http = axios.create({
  baseURL: `${locationOrigin}/o/sample-vue/v1.0/`,
  headers: {
    'Accept': 'application/xml',
    'Content-Type': 'application/xml',
  },
})