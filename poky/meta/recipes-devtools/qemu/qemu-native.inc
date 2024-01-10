inherit native

require qemu.inc

SRC_URI = "https://download.qemu.org/${BPN}-${PV}.tar.xz \
           file://powerpc_rom.bin \
           file://0001-sdl.c-allow-user-to-disable-pointer-grabs.patch \
           file://0002-qemu-Add-missing-wacom-HID-descriptor.patch \
           file://0003-Add-subpackage-ptest-which-runs-all-unit-test-cases-.patch \
           file://run-ptest \
           file://0004-qemu-Add-addition-environment-space-to-boot-loader-q.patch \
           file://0005-qemu-disable-Valgrind.patch \
           file://0006-qemu-Limit-paths-searched-during-user-mode-emulation.patch \
           file://0007-qemu-native-set-ld.bfd-fix-cflags-and-set-some-envir.patch \
           file://0008-chardev-connect-socket-to-a-spawned-command.patch \
           file://0009-apic-fixup-fallthrough-to-PIC.patch \
           file://0010-linux-user-Fix-webkitgtk-hangs-on-32-bit-x86-target.patch \
           file://0011-Revert-linux-user-fix-mmap-munmap-mprotect-mremap-sh.patch \
           file://0001-Add-a-missing-X11-include.patch \
           file://0014-linux-user-fix-to-handle-variably-sized-SIOCGSTAMP-w.patch \
           file://0001-linux-user-assume-__NR_gettid-always-exists.patch \
           file://0001-linux-user-rename-gettid-to-sys_gettid-to-avoid-clas.patch \
           file://0012-fix-libcap-header-issue-on-some-distro.patch \
           file://0013-cpus.c-Add-error-messages-when-qemi_cpu_kick_thread-.patch \
           file://0001-remove-deprecated-references-to-stime.patch \
           "
           
EXTRA_OECONF_append = " --python=python2.7"

EXTRA_OEMAKE_append = " LD='${LD}' AR='${AR}' OBJCOPY='${OBJCOPY}' LDFLAGS='${LDFLAGS}'"

LDFLAGS_append = " -fuse-ld=bfd"

do_install_append() {
     ${@bb.utils.contains('PACKAGECONFIG', 'gtk+', 'make_qemu_wrapper', '', d)}
}
