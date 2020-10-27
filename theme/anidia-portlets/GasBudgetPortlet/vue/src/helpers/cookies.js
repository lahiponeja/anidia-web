export const CookiesHelpers = {
  get: () =>
    Object.fromEntries(document.cookie.split('; ').map(x => x.split('='))),
  set: (name, value, days = 7, path = '/') => {
    const expires = new Date(Date.now() + days * 864e5).toUTCString()
    document.cookie = `${name}=${encodeURIComponent(
      value
    )}; expires=${expires}; path=${path}`
  },
  delete: (name, path = '/') => {
    this.set(name, '', -1, path)
  }
}