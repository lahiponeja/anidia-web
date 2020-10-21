import axios from 'axios'

export const http = axios.create({
  baseURL: 'http://localhost:8080/o/sample-vue/v1.0/',
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
    'Access-Control-Allow-Origin': '*',
    'Accept': 'application/xml',
  },
})