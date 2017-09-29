local io = require("io"); 

local baseServerDir = ngx.var.baseDir
local imageDimension = ngx.var.width .. "x" .. ngx.var.height;
local srcPath = ngx.var.request_filepath;
local destPath = ngx.var.dest_filepath.."_"..ngx.var.width.."x"..ngx.var.height.."."..ngx.var.ext;

srcPath=ngx.unescape_uri(srcPath);
destPath=ngx.unescape_uri(destPath);

function file_exists(path)
  local file = io.open(path, "r");
  if file then file:close() end
  return file ~= nil
end

local command = "/opt/app/image_server/conf/MakeImage.sh "..imageDimension.." '"..srcPath.."' '"..destPath.."' "..ngx.var.ext;
ngx.log(ngx.ERR,command);
if file_exists(srcPath) then
	local x = os.execute(command);
	ngx.exec(ngx.var.uri);
else
	ngx.exit(404);
end; 
