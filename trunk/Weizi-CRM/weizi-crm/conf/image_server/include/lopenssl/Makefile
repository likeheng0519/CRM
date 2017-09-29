TARGET = lopenssllib.so

CC = gcc
LIBS = -lssl
CFLAGS = -g -Wall -fPIC
CFLAGS += -I/usr/local/include/luajit-2.0
LOCAL_SHARE_LIB=/usr/local/lib/lua/5.1
LDFLAGS=
LDADD=

.PHONY: default all clean

default: $(TARGET)
all: default

OBJECTS = $(patsubst %.c, %.o, $(wildcard *.c))
HEADERS = $(wildcard *.h)

%.o: %.c $(HEADERS)
	$(CC) -c $(CFLAGS) -o $@ $<

$(TARGET): $(OBJECTS)
	$(CC) -shared $(LDFLAGS) $(OBJECTS) -o $@ $(LDADD) $(LIBS) -lcrypto -lssl -lrt -ldl

install: all
	mv $(TARGET) $(LOCAL_SHARE_LIB)

clean:
	rm -f $(OBJECTS)
	rm -f $(TARGET)
	rm -f core.*

.PHONY: clean
