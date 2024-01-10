DESCRIPTION = "argos3-epuck installation"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=0d87dc8d54de5bc6ebf8bc2977398756"

DEPENDS = "argos3"
inherit cmake

SRCREV = "748ac12afe9f06348339d72a504e51259801091e"
#"0af32eb241b5ca056018771cb900008a2f7df87d"
SRC_URI = "git://github.com/demiurge-project/argos3-epuck;protocol=http \
file://fix_yocto.patch \
"


S = "${WORKDIR}/git/src"

EXTRA_OECMAKE = "-DARGOS_BUILD_FOR=epuck -DARGOS_INCLUDE_DIRS=${STAGING_DIR_HOST}/usr/include/ -DARGOS_LIBRARY_DIRS=${STAGING_DIR_HOST}/usr/lib/argos3"


FILES_${PN} += "${libdir}/*"	
#/usr/lib
FILES_${PN} += "${prefix}/doc/*"  	
#/usr/doc
FILES_${PN} += "${datadir}/*"		
#/usr/shared
FILES_${PN}-dbg += "/usr/debug/.debug"
TARGET_CC_ARCH += "${LDFLAGS}" 





