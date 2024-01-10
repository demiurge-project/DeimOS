SUMMARY = "Python Qt5 Bindings"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
HOMEPAGE = "http://riverbankcomputing.co.uk"
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
"
#https://www.riverbankcomputing.com/static/Downloads/PyQt5/${PV}/PyQt5_gpl-${PV}.tar.gz

SRC_URI = "\
	https://files.pythonhosted.org/packages/7c/5b/e760ec4f868cb77cee45b4554bf15d3fe6972176e89c4e3faac941213694/PyQt5-${PV}.tar.gz \
"
SRC_URI[md5sum] = "9afe6f358054721474cf26a4eb20b70a"
SRC_URI[sha256sum] = "0145a6b7de15756366decb736c349a0cb510d706c83fda5b8cd9e0557bc1da72"
S = "${WORKDIR}/PyQt5-${PV}"

inherit qmake5
DEPENDS = "qtbase"

export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

PARALLEL_MAKEINST = ""

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager"

DISABLED_FEATURES_append_arm = " PyQt_qreal_double"

PYQT_MODULES = "QtCore QtGui QtNetwork QtWidgets"
PYQT_MODULES_aarch64 = "QtCore QtGui QtNetwork QtWidgets"

do_configure_prepend() {
    cd ${S}
    echo "py_platform = linux" > pyqt.cfg
    echo "py_inc_dir = %(sysroot)/$includedir/python%(py_major).%(py_minor)${PYTHON_ABI}" >> pyqt.cfg
    echo "py_pylib_dir = %(sysroot)/${libdir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "py_pylib_lib = python$%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> pyqt.cfg
    echo "pyqt_bin_dir = ${D}/${bindir}" >> pyqt.cfg
    echo "pyqt_sip_dir = ${D}/${datadir}/sip/PyQt5" >> pyqt.cfg
    echo "pyuic_interpreter = ${D}/${bindir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_disabled_features = ${DISABLED_FEATURES}" >> pyqt.cfg
    echo "qt_shared = True" >> pyqt.cfg
    QT_VERSION=`${OE_QMAKE_QMAKE} -query QT_VERSION`
    echo "[Qt $QT_VERSION]" >> pyqt.cfg
    echo "pyqt_modules = ${PYQT_MODULES}" >> pyqt.cfg
    echo yes | ${PYTHON} configure.py --verbose --qmake  ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake --configuration pyqt.cfg --sysroot ${STAGING_DIR_HOST}
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    cd ${S}
    oe_runmake MAKEFLAGS='-j 1' install
}

FILES_${PN} += "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/sip/PyQt5/"

RDEPENDS_${PN} = "qtbase"
