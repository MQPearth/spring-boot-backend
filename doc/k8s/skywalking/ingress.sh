kubectl create ingress skywalking-ui -n skywalking-ui --class=nginx  --rule="skywalking-ui.k8s.com/*=skywalking-ui:8080"