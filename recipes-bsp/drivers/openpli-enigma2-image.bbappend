BRAND_TYPE?="worldvision"

RDEPENDS += " \
            3rdparty-system-plugins \
            enigma2-3rdparty-plugins \
            extra-plugins \
            dags-shutdown \
			splash-bootlogo \
            \
            libav \
            ntp \
            x264 \
            \
            "

ENIGMA2_PLUGINS += " \
                    enigma2-plugin-systemplugins-networkwizard \
                    enigma2-plugin-systemplugins-wirelesslan \
                    \
                    ${@base_contains("MACHINE","force2solid","", " \
                    enigma2-plugin-systemplugins-tempfancontrol", d)} \
                    \
                    enigma2-plugin-systemplugins-videoenhancement \
                    enigma2-plugin-systemplugins-autobouquetsmaker \
                    enigma2-plugin-systemplugins-satelliteequipmentcontrol \
					enigma2-plugin-systemplugins-crossepg \
                    enigma2-plugin-extensions-cooltvguide \
                    enigma2-plugin-extensions-permanenttimeshift \
                    enigma2-plugin-extensions-mytube \
					\
					${@base_contains("BRAND_TYPE", "worldvision", " \
                    enigma2-plugin-extensions-backupsuite \
                    enigma2-plugin-systemplugins-networkbrowser \
                    enigma2-plugin-systemplugins-3gmodemmanager \
					enigma2-plugin-systemplugins-mountmanager \
                    enigma2-plugin-extensions-epanel", "", d)} \
					\
                    "

ENIGMA2_OPTIONAL += " \
                softcams-enigma2-meta \
                    "

IMAGE_INSTALL += " \
                  python-gdata \
                  python-textutils \
                  ntpdate \
                  tuxbox-links \
				  kernel-params \
				  \
                  enigma2-plugin-softcams-newcs \
                  enigma2-plugin-softcams-mgcamd \
                  enigma2-plugin-softcams-cccam221 \
                  \
                  dags-shutdown \
                  \
				  parted \
				  openssl \
                  libav \
                  mtd-utils \
                  "
