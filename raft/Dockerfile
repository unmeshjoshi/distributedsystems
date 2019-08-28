FROM        centos:7
RUN         yum install -y wget
RUN         wget -r --no-parent -A 'epel-release-*.rpm' http://dl.fedoraproject.org/pub/epel/7/x86_64/e/
RUN         rpm -Uvh dl.fedoraproject.org/pub/epel/7/x86_64/e/epel-release-*.rpm
#from https://github.com/tmtsoftware/stil-ansible/blob/master/yml/build_tools.yml
RUN         yum install -y redis-2.8.19-2.el7.x86_64
EXPOSE      6379
CMD         ["redis-server"]
ENTRYPOINT [ "sh", "-c" ]
