import request from '@/utils/request'


export function getVideoUrl(query){
  return request({
    url: '/video/getVideoUrl',
    method: 'get',
    params: query,
  })
}