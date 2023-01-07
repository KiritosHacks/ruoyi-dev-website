import request from '@/utils/request'


export function getVideo(){
    return request({
        url: '/video/getVideo',
        method: 'get'
      })
}