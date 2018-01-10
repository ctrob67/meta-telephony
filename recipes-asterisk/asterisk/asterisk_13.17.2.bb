DESCRIPTION = "Asterisk is an Open Source PBX and telephony toolkit."
HOMEPAGE = "http://www.asterisk.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3aa955c628d43053f8ba9569d173105a"

DEPENDS = "jansson sqlite3 libedit alsa-lib util-linux libxml2-native"
RDEPENDS_${PN} = "bash"

SRC_URI = "\
    http://downloads.asterisk.org/pub/telephony/asterisk/releases/asterisk-${PV}.tar.gz \
    file://0001-Use-pkgconfig-to-find-sdl.patch \
    file://0002-Use-pkgconfig-to-find-libxml2.patch \
    file://0003-Default-no-native.patch \
    file://asterisk.init.d \
    file://asterisk.service \
"

SRC_URI[md5sum] = "a63d9ff1a393031065c90aa3ac447cff"
SRC_URI[sha256sum] = "19ae0565687b85bde382b2cc274dd5a27ac33dc57a4eb3b663cb5ad9b238f1ac"


S = "${WORKDIR}/asterisk-${PV}"

inherit autotools pkgconfig useradd update-rc.d systemd

SYSTEMD_SERVICE_${PN} = "asterisk.service"

# dora doesn't have autotools-brokensep. Still needed for sysmocom
B = "${S}"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system -g asterisk --shell /bin/false asterisk"
GROUPADD_PARAM_${PN} = "--system asterisk"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'largefile', 'largefile', '', d)} libxml2 neon"
# Optional features
PACKAGECONFIG[dev-mode] = "--enable-dev-mode,--disable-dev-mode"
PACKAGECONFIG[coverage] = "--enable-coverage,--disable-coverage"
PACKAGECONFIG[xmldoc] = "--enable-xmldoc,--disable-xmldoc"
PACKAGECONFIG[largefile] = "--enable-largefile,--disable-largefile"
PACKAGECONFIG[internal-poll] = "--enable-internal-poll,--disable-internal-poll"
PACKAGECONFIG[asteriskssl] = "--enable-asteriskssl,--disable-asteriskssl,openssl"
PACKAGECONFIG[rpath] = "--enable-rpath,--disable-rpath"
# Optional packages
PACKAGECONFIG[bfd] = "--with-bfd,--without-bfd"
PACKAGECONFIG[bluetooth] = "--with-bluetooth,--without-bluetooth,bluez5"
PACKAGECONFIG[backtrace] = "--with-execinfo,--without-execinfo"
PACKAGECONFIG[cap] = "--with-cap,--without-cap,libcap"
PACKAGECONFIG[corosync] = "--with-cpg,--without-cpg,corosync"
PACKAGECONFIG[curses] = "--with-curses,--without-curses,ncurses"
PACKAGECONFIG[crypt] = "--with-crypt,--without-crypt"
PACKAGECONFIG[crypto] = "--with-crypto,--without-crypto"
PACKAGECONFIG[dahdi] = "--with-dahdi,--without-dahdi,dahdi-tools"
# To enable FFMPEG add LICENSE_FLAGS_WHITELIST = "commercial" to your local.conf
PACKAGECONFIG[ffmpeg] = "--with-avcodec,--without-avcodec,libav"
PACKAGECONFIG[gsm] = "--with-gsm,--without-gsm,libgsm"
PACKAGECONFIG[ilbc] = "--with-ilbc,--without-ilbc"
PACKAGECONFIG[gtk2] = "--with-gtk2,--without-gtk2,gtk+"
PACKAGECONFIG[gmime] = "--with-gmime,--without-gmime"
PACKAGECONFIG[openh323] = "--with-h323,--without-h323"
PACKAGECONFIG[hoard] = "--with-hoard,--without-hoard"
PACKAGECONFIG[ical] = "--with-ical,--without-ical"
PACKAGECONFIG[iconv] = "--with-iconv,--without-iconv,libiconv"
PACKAGECONFIG[iksemel] = "--with-iksemel,--without-iksemel,iksemel"
PACKAGECONFIG[imap_tk] = "--with-imap,--without-imap,uw-imap"
PACKAGECONFIG[inotify] = "--with-inotify,--without-inotify"
PACKAGECONFIG[iodbc] = "--with-iodbc,--without-iodbc"
PACKAGECONFIG[isdnnet] = "--with-isdnnet,--without-isdnnet"
# Jack depends on kqeueu
PACKAGECONFIG[jack] = "--with-jack,--without-jack,jack"
PACKAGECONFIG[uriparser] = "--with-uriparser,--without-uriparser"
PACKAGECONFIG[kqueue] = "--with-kqueue,--without-kqueue"
PACKAGECONFIG[ldap] = "--with-ldap,--without-ldap,openldap"
PACKAGECONFIG[libcurl] = "--with-libcurl,--without-libcurl,curl"
PACKAGECONFIG[libxml2] = "--with-libxml2,--without-libxml2,libxml2"
PACKAGECONFIG[libxslt] = "--with-libxslt,--without-libxslt,libxslt"
PACKAGECONFIG[ltdl] = "--with-ltdl,--without-ltdl,libtool"
PACKAGECONFIG[lua] = "--with-lua,--without-lua,lua"
PACKAGECONFIG[misdn] = "--with-misdn,--without-misdn,misdn-utils"
PACKAGECONFIG[mysqlclient] = "--with-mysqlclient,--without-mysqlclient,mariadb"
PACKAGECONFIG[nbs] = "--with-nbs,--without-nbs"
PACKAGECONFIG[ncurses] = "--with-curses,--without-curses,ncurses"
PACKAGECONFIG[neon] = "--with-neon,--without-neon,neon"
PACKAGECONFIG[netsnmp] = "--with-netsnmp,--without-netsnmp,net-snmp"
PACKAGECONFIG[newt] = "--with-newt,--without-newt,libnewt"
PACKAGECONFIG[ogg] = "--with-ogg,--without-ogg,libogg"
PACKAGECONFIG[openr2] = "--with-openr2,--without-openr2"
PACKAGECONFIG[opus] = "--with-opus,--without-opus,libopus"
PACKAGECONFIG[osptk] = "--with-osptk,--without-osptk"
PACKAGECONFIG[oss] = "--with-oss,--without-oss,alsa-oss"
PACKAGECONFIG[pgsql] = "--with-postgres,--without-postgres,postgresql"
PACKAGECONFIG[pjproject] = "--with-pjproject,--without-pjproject,pjproject"
PACKAGECONFIG[pjprojectbundled] = "--with-pjproject-bundled,,"
PACKAGECONFIG[popt] = "--with-popt,--without-popt,popt"
PACKAGECONFIG[portaudio] = "--with-portaudio,--without-portaudio,portaudio-v19"
PACKAGECONFIG[pri] = "--with-pri,--without-pri,misdn-utils"
PACKAGECONFIG[pwlib] = "--with-pwlib,--without-pwlib,pwlib"
PACKAGECONFIG[radius] = "--with-radius,--without-radius,radiusclient-ng"
PACKAGECONFIG[resample] = "--with-resample,--without-resample,ncurses"
# Need to enable sdl_images to use sdl
PACKAGECONFIG[sdl] = "--with-sdl,--without-sdl,libsdl"
PACKAGECONFIG[sdl_image] = "--with-SDL_image,--without-SDL_image,libsdl-image"
PACKAGECONFIG[sounds-cache] = "--with-sounds-cache,--without-sounds-cache"
PACKAGECONFIG[spandsp] = "--with-spandsp,--without-spandsp"
PACKAGECONFIG[ss7] = "--with-ss7,--without-ss7"
PACKAGECONFIG[speex] = "--with-speex,--without-speex,speex"
PACKAGECONFIG[speexdsp] = "--with-speexdsp,--without-speexdsp,speexdsp"
PACKAGECONFIG[srtp] = "--with-srtp,--without-srtp"
PACKAGECONFIG[openssl] = "--with-ssl,--without-ssl,openssl"
PACKAGECONFIG[suppserv] = "--with-suppserv,--without-suppserv,misdn-utils"
PACKAGECONFIG[freetds] = "--with-tds,--without-tds"
PACKAGECONFIG[termcap] = "--with-termcap,--without-termcap,libcap"
PACKAGECONFIG[timerfd] = "--with-timerfd,--without-timerfd"
PACKAGECONFIG[tinfo] = "--with-tinfo,--without-tinfo,ncurses"
PACKAGECONFIG[tonezone] = "--with-tonezone,--without-tonezone"
PACKAGECONFIG[unixodbc] = "--with-unixodbc,--without-unixodbc,libodbc"
PACKAGECONFIG[vorbis] = "--with-vorbis,--without-vorbis,libvorbis"
PACKAGECONFIG[vpb] = "--with-vpb,--without-vpb"
PACKAGECONFIG[x11] = "--with-x11,--without-x11,libx11"
PACKAGECONFIG[zlib] = "--with-z,--without-z,zlib"

EXTRA_OECONF += " \
   --with-libedit=${STAGING_INCDIR} \
"

INITSCRIPT_NAME = "asterisk"
INITSCRIPT_PARAMS = "defaults"

do_patch_append() {
    # copy the pjproject macro config to a location that is searched
    import shutil
    s = d.getVar('S', True)
    src_dir = os.path.join(s, 'third-party/pjproject')
    dest_dir = os.path.join(s, 'pjproject')
    if not os.path.isdir(dest_dir):
        # copy only if the directory does not exist already 
        shutil.copytree(src_dir, dest_dir)
}

do_configure_append() {
    echo "Regenerate the configure scripts"
    for d in ${S}/menuselect; do
        echo " - $d"
        (cd $d; autoreconf -I ${S}/autoconf)
    done

    echo "Starting the build of menuselect"
    cd ${B}/menuselect
    CC="${HOST_CC}" \
    CXX="${HOST_CXX}" \
    LD="" \
    AR="" \
    RANLIB="" \
    CFLAGS="${HOST_CFLAGS}" \
    LDFLAGS="${HOST_LDFLAGS}" \
    ./configure

    cd ${B}
    make menuselect.makeopts
}

do_install_append() {
    oe_runmake DESTDIR=${D} samples
    install -Dm 0755 ${WORKDIR}/asterisk.init.d ${D}${sysconfdir}/init.d/asterisk
    # Set file permissions and ownerships
    chown -R root:asterisk ${D}${sysconfdir}/asterisk
    chmod -R u=rwX,g=rwX,o= ${D}${sysconfdir}/asterisk
    for x in spool lib log; do
        chown -R asterisk:asterisk ${D}${localstatedir}/${x}/asterisk
        chmod -R u=rwX,g=rwX,o= ${D}${localstatedir}/${x}/asterisk
    done
    # This is sym-linked elsewhere to /run so this dir conflicts with symlink
    rm -rf ${D}${localstatedir}/run
    install -Dm 0644 ${WORKDIR}/asterisk.service ${D}/lib/systemd/system/asterisk.service
}

FILES_${PN} += "\
    ${datadir}/dahdi/* \
    ${libdir}/hotplug/firmware \
"
FILES_${PN}-dbg += "${libdir}/*/*/.debug"
