FROM centos:7.0.1406 AS builder

# install build dependencies
COPY metwork.repo /etc/yum.repos.d/metwork.repo
RUN yum swap -y fakesystemd systemd && \
    yum update -y && \
    yum install -y metwork-mfdata-full make && \
    yum clean all

# buid the plugin
USER mfdata
WORKDIR /home/mfdata/metwork/mfdata/
COPY --chown=mfdata:metwork plugins/convert_png_to_jpeg convert_png_to_jpeg
RUN source /opt/metwork-mfdata/share/interactive_profile && \
    mfdata.start && \
    pushd convert_png_to_jpeg && \
    make release && \
    popd


FROM centos:7.0.1406

COPY metwork.repo /etc/yum.repos.d/metwork.repo
RUN yum swap -y fakesystemd systemd && \
    yum update -y && \
    yum install -y \
      # install metwork
      metwork-mfext-full metwork-mfdata-full metwork-mfserv-full crontabs \
      # install convert_png_to_jpeg plugin dependency
      ImageMagick && \
    yum clean all

# install the plugin
COPY --from=builder \
     /home/mfdata/metwork/mfdata/convert_png_to_jpeg/*.plugin \
     /etc/metwork.config.d/mfdata/external_plugins/

COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
