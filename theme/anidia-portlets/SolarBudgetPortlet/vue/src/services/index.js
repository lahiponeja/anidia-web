import axios from 'axios'

const locationOrigin = window.location.origin

export const http = axios.create({
  baseURL: `${locationOrigin}/o/gas-budget/v1.0/`,
  headers: {
    'Accept': 'application/xml',
    'Content-Type': 'application/xml',
  },
})

export const httpSolar = axios.create({
  baseURL: `${locationOrigin}/o/solar-budget/v1.0/`,
  headers: {
    'Accept': 'application/xml',
    'Content-Type': 'application/xml',
  },
})