## 指定命名空间 指定 转发规则 80是pod对内的端口 意思为user-api.k8s.com的请求都转发至user-api-service
kubectl create ingress user-api -n app --class=nginx  --rule="user-api.k8s.com/*=user-api:80"