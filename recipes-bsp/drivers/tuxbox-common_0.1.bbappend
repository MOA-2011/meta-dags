SRC_URI += " \
            file://terrestrial.xml \
            "
S="${WORKDIR}"

do_install_append() {
    install -m 0644 ${S}/terrestrial.xml ${D}/etc/tuxbox
}
