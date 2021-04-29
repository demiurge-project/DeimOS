require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":k3"

require conf/machine/include/arm/arch-arm64.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r0"

PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

KERNEL_IMAGETYPE = "Image"
KERNEL_IMAGETYPES = "Image vmlinux.gz"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

SPL_BINARY = "tispl.bin"
UBOOT_SUFFIX = "img"

EXTRA_IMAGEDEPENDS += "virtual/bootloader"

MACHINE_GUI_CLASS = "smallscreen"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features, may need to add touchscreen for specific machines
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet pci gpu"
# Not ready yet - dsp mmip

IMAGE_FSTYPES += "tar.xz wic.xz"

# Please note that booting K3 platform also requires
# tiboot3.bin and sysfw.itb, which are provided by meta-arago
# u-boot-k3-r5 and ti-sci-fw recipes
IMAGE_BOOT_FILES ?= "${SPL_BINARY} u-boot.${UBOOT_SUFFIX}"
WKS_FILE ?= "sdimage-bootpart.wks"
do_image_wic[depends] += "u-boot:do_deploy"