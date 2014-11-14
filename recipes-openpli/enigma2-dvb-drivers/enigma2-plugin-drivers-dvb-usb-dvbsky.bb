DESCRIPTION = "USB DVB driver for dvbsky chipset"

require conf/license/openpli-gplv2.inc

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
        ${DVBPROVIDER}-module-dvb-usb-dvbsky \
        ${DVBPROVIDER}-module-dvbsky-m88ds3103 \
		firmware-dvb-usb-megasky-02 \
"

PV = "1.0"
PR = "r3"

ALLOW_EMPTY_${PN} = "1"
