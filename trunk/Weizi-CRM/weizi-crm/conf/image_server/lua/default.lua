local io = require("io"); 
local origFileExtensionName=ngx.var.origExt;
local convertFileExtensionName=ngx.var.ext;
local imageDimension=ngx.var.width .. "x" .. ngx.var.height;


 
 local origFilePath=ngx.var.storagePath.."/"..ngx.var.requestFilePath.."/"..ngx.var.requestFileName;

local srcPath=origFilePath;

local allowedImageDimensions = {"200x200", "1024x768", "40x40"};

function table.contains(table, element)  
             for _, value in pairs(table) do  
                if value == element then  
                   return true  
                end  
             end  
             return false  
          end

function getOutputRealExtension(origExt)
  if(origExt =="gif" or origExt =="GIF") then return "gif" end;
  
  if(origExt =="ico" or origExt =="ICO") then return "png" end;

  
   return "jpg"; 
end  

function file_exists(path)
  local file = io.open(path, "rb")
  if file then file:close() end
  return file ~= nil
end

function convertSrcFile(srcPath,origExt)
  
   ngx.log(ngx.ERR,"convertSrcFile"..srcPath);
 if(origExt =="ico" or origExt =="ICO") then return origExt..":"..srcPath.."[5]" end;
  
 if(origExt =="psd" or origExt =="PSD") then return origExt..":"..srcPath.."[0]" end;
  return origExt..":"..srcPath;

end


 

local outputExt=getOutputRealExtension(ngx.var.origExt);
local thumbnailOutputFileName=ngx.var.requestFileName.."_".. imageDimension.."."..outputExt;
local thumbnailOutputFilePath=ngx.var.requestFilePath.."/"..thumbnailOutputFileName;
local  fullThumbnailOutputFilePath=ngx.var.storagePath.."/"..thumbnailOutputFilePath;

 
 if file_exists(fullThumbnailOutputFilePath) then
   ngx.exec("/image/"..thumbnailOutputFilePath);
   return;
 end;
 

  	  
srcPath=convertSrcFile(srcPath,origFileExtensionName);
  
  local command =ngx.var.baseDir.."/conf/MakeImage.sh "..imageDimension.." "..srcPath.." "..fullThumbnailOutputFilePath .." "..origFileExtensionName;
  ngx.log(ngx.ERR,command);
  local x=os.execute(command);
  ngx.exec("/image/"..thumbnailOutputFilePath);

