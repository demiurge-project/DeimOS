DESCRIPTION = "Realtek 8192cu USB wifi driver Install"
PR = "r1"
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"

SRC_URI="file://rtl8192cu"



S = "${WORKDIR}/rtl8192cu"

inherit module

do_compile() {
        cd ${S}
	ls -l
        unset LDFLAGS
        oe_runmake KSRC=${STAGING_KERNEL_DIR} CFLAGS='${CFLAGS}'
        ${TARGET_SYS}-strip --strip-debug rtl8192cu.ko
}

do_install() {
  install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
  install -m 0644 ${S}/rtl8192cu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

