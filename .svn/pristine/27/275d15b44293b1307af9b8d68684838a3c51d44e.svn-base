#include <stdlib.h>
#include <string.h>
#include <openssl/rc4.h>
#include "lua.h"
#include "lauxlib.h"

#ifdef OPENSSL_SYS_MSDOS
	/* This should set the file to binary mode. */
	{
#include <fcntl.h>
		setmode(fileno(in),O_BINARY);
		setmode(fileno(out),O_BINARY);
	}
#endif

static int l_rc4_options (lua_State *L)
{
  lua_pushstring(L, RC4_options());
  return 1;
}

static int l_rc4_encrypt (lua_State *L)
{
  RC4_KEY key;
  FILE *in=NULL,*out=NULL;
  char buf[BUFSIZ];
  int i=0;
  
  size_t key_len;
  const char *keystr = luaL_checklstring(L, 1, &key_len);
  
  size_t infile_len;
  const char *infile = luaL_checklstring(L, 2, &infile_len);

  size_t outfile_len;
  const char *outfile = luaL_checklstring(L, 3, &outfile_len);
  
  RC4_set_key(&key, (int)key_len, (const unsigned char *)keystr);
  
  //Discard first 768 bytes of the key stream
  //unsigned char keyBuff[768];
 // memset(keyBuff, '0', sizeof(keyBuff));
 // RC4(&key, 768, keyBuff, keyBuff);
	
  in=fopen(infile,"r");
  out=fopen(outfile,"w");	

  for(;;)
  	{
  		i=fread(buf,1,BUFSIZ,in);
  		if (i == 0) break;
  		if (i < 0)
  		{
  			break;
  		}
  		RC4(&key,(unsigned int)i,(unsigned char *)buf,
  			(unsigned char *)buf);
  		i=fwrite(buf,(unsigned int)i,1,out);
  		if (i != 1)
  		{
  			break;
  		}
  	}

  fclose(out);
  fclose(in);

  return 1;
}

static int l_rc4 (lua_State *L)
{
  size_t len;
  const char *data = luaL_checklstring(L, 1, &len);

  lua_newuserdata(L, sizeof(RC4_KEY));
  RC4_set_key((RC4_KEY *)lua_touserdata(L, -1), (int)len, (const unsigned char *)data);

  lua_pushcclosure(L, l_rc4_encrypt, 1);

  return 1;
}

static const struct luaL_Reg openssllib[] = {
  { "rc4_options", l_rc4_options },
  { "rc4", l_rc4 },
  { "rc4_crypt", l_rc4_encrypt },
  { NULL, NULL }
};

LUALIB_API int luaopen_lopenssllib(lua_State *L) {
  luaL_register(L, "lopenssllib", openssllib);

  return 1;
}
