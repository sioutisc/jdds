#
# $Id$
#
JTOOLS_HOME = ../../..
JTOOLS_CLASSES = ${JTOOLS_HOME}/classes
JTOOLS_SRC = ${JTOOLS_HOME}/src
JTOOLS_LIB = ${JTOOLS_HOME}/lib
JTOOLS_BIN = ${JTOOLS_HOME}/bin
JNI_INCLUDE = ${JAVA_HOME}/include
JNI_LINUX_INCLUDE = ${JAVA_HOME}/include/linux
GCJ = gcj
GCC = gcc
GPP = g++
GCJH = gcjh


#
# jamaica (2.8 and 3.0) modifications - used by 'jamaica' target
# you should change JAMAICA_TARGET if the target differs from i686.
#

JAMAICA_TARGET = linux-gnu-i686
JAMAICA_INCLUDE_PATH = $(JAMAICA)/include $(JAMAICA)/target/$(JAMAICA_TARGET)/include /usr/X11/include
JAMAICA_CINCLUDES = $(foreach z,$(JAMAICA_INCLUDE_PATH),-I$z)
JAMAICA_BUILDER = $(JAMAICA)/bin/jamaica
JAMAICAH = $(JAMAICA)/bin/jamaicah
JAMAICA_GCC_FLAGS = -DHAVE_BOOL -DJAMAICA

jamaica:
	python genSysInfo.py
	jamaicah -jni -d . -classpath ${JTOOLS_CLASSES} jtools.time.HighResClock
	${GPP} -O3 ${JAMAICA_GCC_FLAGS} $(JAMAICA_CINCLUDES) -shared jtools_time_HighResClock.cpp -o ${JTOOLS_LIB}/libHRTime.so




all:
	python genSysInfo.py
	javah -jni -d . -classpath ${JTOOLS_CLASSES} jtools.time.HighResClock
	${GCJH}  -I${JTOOLS_CLASSES} jtools.time.HighResClock -d . -o HighResClock.h
	${GCJH}  -I${JTOOLS_CLASSES} jtools.time.HighResTime -d . -o HighResTime.h
	${GPP} -O3 -I${JNI_INCLUDE} -I${JNI_LINUX_INCLUDE}  -shared jtools_time_HighResClock.cpp -o ${JTOOLS_LIB}/libHRTime.so

clean:
	rm ${JTOOLS_LIB}/libHRTime.so
	rm ./*.h
	rm ./*.o
