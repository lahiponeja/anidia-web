import xmlToJson from './xmlToJson';

export default function xmlToJsonImp(res) {
  const XmlString = res;
  const XmlNode = new DOMParser().parseFromString(XmlString, 'text/xml');
  const json = xmlToJson(XmlNode);
  return json;
}