DESCRIPTION = "argos3 installation"

LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"

#DEPENDS += "lua"
#DEPENDS += "asciidoc"

inherit cmake
SRCREV = "52c6b341bc4339c0fa2a5d12ee56be2d48794519"
#"623fb873eaaddb22747dceb13f917790bad33f5e"
SRC_URI = "git://github.com/ilpincy/argos3;protocol=http \
file://fix_yocto.patch \
" 
S = "${WORKDIR}/git/src"

EXTRA_OECMAKE = "-DARGOS_BUILD_FOR=epuck -DARGOS_DOCUMENTATION=OFF -DARGOS_DYNAMIC_LIBRARY_LOADING=OFF -DARGOS_THREADSAFE_LOG=OFF -DARGOS_USE_DOUBLE=OFF"

FILES_${PN} += "${libdir}/*"	
#/usr/lib
FILES_${PN} += "${prefix}/doc/*"  	
#/usr/doc
FILES_${PN} += "${datadir}/*"		
#/usr/shared
FILES_${PN}-dbg += "/usr/debug/.debug"
TARGET_CC_ARCH += "${LDFLAGS}" 





