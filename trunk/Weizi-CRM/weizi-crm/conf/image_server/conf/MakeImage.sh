#!/bin/bash

CONVERT=/usr/local/bin/convert
IDENTIFY=/usr/local/bin/identify

echo "-------------------------------------"

echo $CMD
var1=$3
var2=${var1%/*}
#var3=${var2/ftp/thumbnail}
mkdir -p $var2
var4=${var1##*/}


echo "-------------------------------------"

oldIFS=$IFS
IFS=*

CMD="$CONVERT*$2*-resize*$1>*-auto-orient*-strip*-quality*80*$3"

CONVERT_NORMAL="$CONVERT*$1*$2"

GIF_CMD="$CONVERT*$2*-coalesce*-bordercolor*LightSteelBlue*-border*0*-resize*$1*-layers*Optimize*$3"

echo $CMD
IFS=$oldIFS

case "$4" in
  gif|GIF)
     IFS=*
     $GIF_CMD
        ;;
  *)
     IFS=*
     $CMD
esac

IFS=$oldIFS