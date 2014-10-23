MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	dags-dvb-modules-${CHIP} \
	"

MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

include conf/machine/include/autoload-filesystems.inc

MACHINE_EXTRA_RRECOMMENDS = " \
	gst-plugin-dvbmediasink \
    ntfs-3g \
	"


TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

PREFERRED_PROVIDER_virtual/kernel = "linux-dags"
PREFERRED_VERSION_linux-libc-headers = "3.9.7"

GLIBC_EXTRA_OECONF = "--with-tls"
DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dts"


KV = "linux-kernel-3.9.7"


IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096 -F"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

IMAGEDIR ?= "${MACHINE}"
EXTRA_IMAGECMD_COMPAT = " --eraseblock=0x20000 -n -l --pagesize=0x800 "

IMAGE_CMD_ubi_prepend = " \
        mkfs.jffs2 --root=${IMAGE_ROOTFS}/boot --faketime \
        --disable-compressor=lzo --compression-mode=size \
        --output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 \
        ${EXTRA_IMAGECMD_COMPAT}; rm -rf ${IMAGE_ROOTFS}/boot/; \
        "

IMAGE_CMD_ubi_append = " \
        mkdir -p ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/; \
        cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/oe_rootfs.bin; \
        cp ${DEPLOY_DIR_IMAGE}/splash.bmp ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/; \
        cp ${DEPLOY_DIR_IMAGE}/splash1.bmp ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/; \
        cp ${DEPLOY_DIR_IMAGE}/splash2.bmp ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/; \
        cp ${DEPLOY_DIR_IMAGE}/splash3.bmp ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/; \
        gzip -f -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/update/${MACHINE}/cfe/oe_kernel.bin; \
        cd ${DEPLOY_DIR_IMAGE}; \
        zip ${IMAGE_NAME}-${KV}-${DATE}-usb.zip update/${MACHINE}/cfe/*;rm -rf update \
       " 

TARGET_FPU = "hard"

MACHINE_FEATURES += "alsa usbhost wlan kernelwifi 3dtv switchoff osdposition hdmicec newkernel"

require conf/machine/include/tune-mips32.inc
