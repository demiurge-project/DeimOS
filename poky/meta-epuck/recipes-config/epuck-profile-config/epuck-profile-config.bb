SUMMARY = "config profile for the e-puck"
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"

SRC_URI = "\
 file://epuck_profile.sh \
"

do_configure(){
:
}

do_compile() {
:
}


do_install() {
	install -d ${D}${sysconfdir}/profile.d
	install -m 0775 ${WORKDIR}/epuck_profile.sh ${D}${sysconfdir}/profile.d/epuck_profile.sh
}
