BRAND_TYPE?="worldvision"

DESCRIPTION = "first bootlogo splash image"
SECTION = "base"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/openpli-gplv2.inc

PV = "1.0"
PR = "r0"

S = "${WORKDIR}/"

SRC_URI = " \
           file://wo_splash.bmp \
           file://wo_splash1.bmp \
           file://wo_splash2.bmp \
           file://wo_splash3.bmp \
           file://iq_splash.bmp \
           file://iq_splash1.bmp \
           file://iq_splash2.bmp \
           file://iq_splash3.bmp \
			"
inherit deploy

do_deploy() {
    if [ "${BRAND_TYPE}" = "worldvision" ]; then
        install -m 0644 wo_splash.bmp ${DEPLOYDIR}/splash.bmp
        install -m 0644 wo_splash1.bmp ${DEPLOYDIR}/splash1.bmp
        install -m 0644 wo_splash2.bmp ${DEPLOYDIR}/splash2.bmp
        install -m 0644 wo_splash3.bmp ${DEPLOYDIR}/splash3.bmp
    elif [ "${BRAND_TYPE}" = "iqon" ]; then
        install -m 0644 iq_splash.bmp ${DEPLOYDIR}/splash.bmp
        install -m 0644 iq_splash1.bmp ${DEPLOYDIR}/splash1.bmp
        install -m 0644 iq_splash2.bmp ${DEPLOYDIR}/splash2.bmp
        install -m 0644 iq_splash3.bmp ${DEPLOYDIR}/splash3.bmp
    fi
}

addtask deploy before do_build
