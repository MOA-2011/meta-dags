BRAND_TYPE?="worldvision"

MODULE = "OpenWebif"
DESCRIPTION = "Control your receiver with a browser"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://README;firstline=10;lastline=12;md5=9c14f792d0aeb54e15490a28c89087f7"

DEPENDS = "python-cheetah-native"
RDEPENDS_${PN} = "python-textutils python-cheetah python-json python-unixadmin python-misc python-pyopenssl python-shell aio-grab python-compression"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

DEPENDS += "enigma2"

#SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-${MODULE}.git;protocol=git
SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-openwebif.git;protocol=git \
		file://${MACHINE}.jpg \
        file://edision.png \
        file://ed_type0.png \
        file://iqon.png \
        file://mediabox.png \
        file://sw_type0.png \
        file://te_type0.png \
        file://te_type1.png \
        file://te_type2.png \
        file://wo_type0.png \
        file://ed_type1.png \
        file://edision.html \
        file://ed_type0.html \
        file://iqon.html \
        file://mediabox.html \
        file://sw_type0.html \
        file://te_type0.html \
        file://te_type1.html \
        file://te_type2.html \
        file://wo_type0.html \
        file://ed_type1.html \
        file://ed_force1.jpg \
        file://ed_force1plus.jpg \
        file://iq_force1.jpg \
        file://iq_force1plus.jpg \
        file://sw_force1.jpg \
        file://sw_force1plus.jpg \
		file://te_tmnano2super.jpg \
        file://te_force1.jpg \
        file://te_force1plus.jpg \
        file://wo_force1.jpg \
        file://wo_force1plus.jpg \
        file://ru.po \
        file://iqonmicro.jpg \
        file://iqonmicro.html \
        file://iqonmicro.png \
        file://iq_force2solid.jpg \
           "

S="${WORKDIR}/git"

SRCREV_pn-${PN} ?= "${AUTOREV}"

do_configure_prepend() {
    cp ${WORKDIR}/ru.po ${S}/locale
}
# Just a quick hack to "compile" it
do_compile() {
#	cheetah-compile -R --nobackup ${S}/plugin
	python -O -m compileall ${S}
}

PLUGINPATH = "/usr/lib/enigma2/python/Plugins/Extensions/${MODULE}"
do_install_append() {
	install -d ${D}${PLUGINPATH}

    for f in $(find ${S}/locale -name *.po );do
        l=$(echo ${f%} | sed 's/\.po//' | sed 's/.*locale\///')
        mkdir -p ${D}${PLUGINPATH}/locale/${l%}/LC_MESSAGES
        msgfmt -o ${D}${PLUGINPATH}/locale/${l%}/LC_MESSAGES/OpenWebif.mo ${S}/locale/$l.po 
    done

	cp -rp ${S}/plugin/* ${D}${PLUGINPATH}
	cp -rf ${WORKDIR}/*.html ${D}${PLUGINPATH}/public/static/remotes/
	cp -rf ${WORKDIR}/*.png ${D}${PLUGINPATH}/public/images/remotes/

    if [ "${BRAND_TYPE}" = "technomate" ]; then
        cp -rf ${WORKDIR}/te_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
    elif [ "${BRAND_TYPE}" = "swiss" ]; then
        cp -rf ${WORKDIR}/sw_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
    elif [ "${BRAND_TYPE}" = "worldvision" ]; then
        cp -rf ${WORKDIR}/wo_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
    elif [ "${BRAND_TYPE}" = "edision" ]; then
        if [ "${MACHINE}" = "optimussos1plus" ]; then
            cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/ed_op1plus.jpg
        elif [ "${MACHINE}" = "optimussos2plus" ]; then
            cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/ed_op2plus.jpg
        elif [ "${MACHINE}" = "force1plus" ]; then
            cp -rf ${WORKDIR}/ed_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        else
            cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        fi
    elif [ "${BRAND_TYPE}" = "iqon" ]; then
	    cp -rf ${WORKDIR}/iq_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
    else
	    cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
    fi
}

FILES_${PN} = "${PLUGINPATH}"
