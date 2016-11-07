#!/bin/sh

function makedir()
{
    if [ ! -d "$1" ]; then
        echo "  Creating directory $1"
        mkdir "$1"
    fi
}

if [ ! `whoami` = "root" ]; then
    echo "You must be root to run this script."
    exit 1
fi

if [ -z "$NCIBI_HOME" ]; then
    export NCIBI_HOME="/usr/share/ncibi"
fi

echo " "
echo "Setting up NCIBI_HOME..."
echo "  NCIBI_HOME: $NCIBI_HOME"

makedir "$NCIBI_HOME"
chmod a+rwxs "$NCIBI_HOME"

makedir "$NCIBI_HOME"/status

#
# This file will be installed when bap is run.
#
sh ./war-install.sh

echo "Done."
