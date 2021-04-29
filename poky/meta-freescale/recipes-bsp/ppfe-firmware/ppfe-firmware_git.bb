SUMMARY = "PPFE Linux firmware"
LICENSE = "NXP-Binary-EULA"
LIC_FILES_CHKSUM = "file://NXP-Binary-EULA.txt;md5=92723670f432558b9e2494ed177d2a85"


INHIBIT_DEFAULT_DEPS = "1"

inherit deploy fsl-eula-unpack

SRC_URI = "git://github.com/NXP/qoriq-engine-pfe-bin.git;fsl-eula=true;nobranch=1"
SRCREV = "58aa0cac630528ed91fe5639700d6b571c0c07b5"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}/lib/firmware
    install -d ${D}/boot/engine-pfe-bin
    install -m 644 ${S}/NXP-Binary-EULA.txt ${D}/lib/firmware
    install -m 755 ${S}/ls1012a/slow_path/*.elf ${D}/lib/firmware
    install -m 755 ${S}/ls1012a/u-boot/* ${D}/boot/engine-pfe-bin
}

do_deploy () {
    install -d ${DEPLOYDIR}/engine-pfe-bin
    cp -r ${D}/boot/engine-pfe-bin/* ${DEPLOYDIR}/engine-pfe-bin
}

addtask deploy after do_install

FILES_${PN} += "/lib/firmware /boot/"
INSANE_SKIP_${PN} += "arch already-stripped"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

COMPATIBLE_MACHINE = "(ls1012a)"
