SUMMARY = "config rc.local for the e-puck"
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"

SRC_URI = "\
 file://rc.local  \
"

do_configure(){
:
}

do_compile() {
:
}


do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0775 ${WORKDIR}/rc.local ${D}${sysconfdir}/init.d/rc.local
}
