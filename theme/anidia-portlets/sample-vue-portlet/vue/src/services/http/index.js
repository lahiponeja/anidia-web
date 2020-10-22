import axios from 'axios'

const locationOrigin = window.location.origin

export const http = axios.create({
  baseURL: `${locationOrigin}/o/sample-vue/v1.0/`,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
    'Access-Control-Allow-Origin': '*',
    'Accept': 'application/xml',
  },
})