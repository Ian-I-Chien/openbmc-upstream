DESCRIPTION = "phosphor-ipmi-flash config to update without verifying the image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://noverify-bmc-update.service"
SRC_URI += "file://noverify-bmc-verify.service"
SRC_URI += "file://config-bmc.json"

SYSTEMD_SERVICE:${PN} += "noverify-bmc-update.service"
SYSTEMD_SERVICE:${PN} += "noverify-bmc-verify.service"

inherit systemd

do_install() {
  install -d ${D}${datadir}/phosphor-ipmi-flash
  install -m 0644 ${UNPACKDIR}/config-bmc.json ${D}${datadir}/phosphor-ipmi-flash
  install -d ${D}${systemd_system_unitdir}
  install -m 0644 ${UNPACKDIR}/noverify-bmc-update.service ${D}${systemd_system_unitdir}
  install -m 0644 ${UNPACKDIR}/noverify-bmc-verify.service ${D}${systemd_system_unitdir}
}

FILES:${PN} += "${datadir}/phosphor-ipmi-flash/config-bmc.json"
