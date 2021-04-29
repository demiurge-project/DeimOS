SUMMARY = "load g_ether module on boot"
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"

SRC_URI = "\
 file://g_ether.conf \
"

do_configure(){
:
}

do_compile() {
:
}


do_install() {
	install -d ${D}${sysconfdir}/modules-load.d
	install -m 0770 ${WORKDIR}/g_ether.conf ${D}${sysconfdir}/modules-load.d/g_ether.conf
}
