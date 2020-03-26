/* ------------------------------------------------------------------------------
 Tool to profile Java library methods
 Author: Andrew McGraw
 ------------------------------------------------------------------------------
 The MIT License (MIT)
 Copyright (c) 2016-2017 Kestrel Technology LLC

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

package Java_Timing;

public class Profiling_Class_Selector{
	public Java_Timings get_profiler(String classname, String output_file, boolean is_verbose) {
        Java_Timings profiler = null;

        switch(classname) {
            case "multianewarray" :
                profiler = new multianewarray_Timings(is_verbose, output_file);
                break;
            case "spark.Request" :
				profiler = new s_Request_Timings(is_verbose);
				break;
			case "spark.Response" :
				profiler = new s_Response_Timings(is_verbose);
				break;
			case "spark.Session" :
				profiler = new s_Session_Timings(is_verbose);
				break;
			case "spark.Spark" :
				profiler = new s_Spark_Timings(is_verbose);
				break;
			case "spark.route.RouteOverview" :
				profiler = new sr_RouteOverview_Timings(is_verbose);
				break;
			case "org.apache.commons.io.FileUtils" :
				profiler = new oaci_FileUtils_Timings(is_verbose);
				break;
			case "org.apache.commons.io.IOUtils" :
				profiler = new oaci_IOUtils_Timings(is_verbose);
				break;
			case "org.apache.commons.fileupload.FileItem" :
				profiler = new oacf_FileItem_Timings(is_verbose);
				break;
			case "org.apache.commons.fileupload.FileItemIterator" :
				profiler = new oacf_FileItemIterator_Timings(is_verbose);
				break;
			case "org.apache.commons.fileupload.FileItemStream" :
				profiler = new oacf_FileItemStream_Timings(is_verbose);
				break;
			case "org.apache.commons.fileupload.FileUpload" :
				profiler = new oacf_FileUpload_Timings(is_verbose);
				break;
			case "org.apache.commons.fileupload.FileUploadBase" :
				profiler = new oacf_FileUploadBase_Timings(is_verbose);
				break;
			case "org.apache.commons.fileupload.disk.DiskFileItemFactory" :
				profiler = new oacfd_DiskFileItemFactory_Timings(is_verbose);
				break;
			case "org.mapdb.DB" :
				profiler = new om_DB_Timings(is_verbose);
				break;
			case "org.mapdb.DBMaker$Maker" :
				profiler = new om_DBMaker$Maker_Timings(is_verbose);
				break;
			case "org.mapdb.DBMaker" :
				profiler = new om_DBMaker_Timings(is_verbose);
				break;
			case "org.apfloat.Apfloat" :
				profiler = new oa_Apfloat_Timings(is_verbose);
				break;
			case "com.google.protobuf.AbstractMessage$Builder" :
				profiler = new cgp_AbstractMessage$Builder_Timings(is_verbose);
				break;
			case "com.google.protobuf.AbstractMessage" :
				profiler = new cgp_AbstractMessage_Timings(is_verbose);
				break;
			case "com.google.protobuf.AbstractMessageLite$Builder" :
				profiler = new cgp_AbstractMessageLite$Builder_Timings(is_verbose);
				break;
			case "com.google.protobuf.AbstractMessageLite" :
				profiler = new cgp_AbstractMessageLite_Timings(is_verbose);
				break;
			case "com.google.protobuf.ByteString" :
				profiler = new cgp_ByteString_Timings(is_verbose);
				break;
			case "com.google.protobuf.CodedInputStream" :
				profiler = new cgp_CodedInputStream_Timings(is_verbose);
				break;
			case "com.google.protobuf.CodedOutputStream" :
				profiler = new cgp_CodedOutputStream_Timings(is_verbose);
				break;
			case "com.google.protobuf.Descriptors$Descriptor" :
				profiler = new cgp_Descriptors$Descriptor_Timings(is_verbose);
				break;
			case "com.google.protobuf.Descriptors$EnumDescriptor" :
				profiler = new cgp_Descriptors$EnumDescriptor_Timings(is_verbose);
				break;
			case "com.google.protobuf.Descriptors$EnumValueDescriptor" :
				profiler = new cgp_Descriptors$EnumValueDescriptor_Timings(is_verbose);
				break;
			case "com.google.protobuf.Descriptors$FileDescriptor" :
				profiler = new cgp_Descriptors$FileDescriptor_Timings(is_verbose);
				break;
			case "com.google.protobuf.GeneratedMessageV3$Builder" :
				profiler = new cgp_GeneratedMessageV3$Builder_Timings(is_verbose);
				break;
			case "com.google.protobuf.GeneratedMessageV3$FieldAccessorTable" :
				profiler = new cgp_GeneratedMessageV3$FieldAccessorTable_Timings(is_verbose);
				break;
			case "com.google.protobuf.GeneratedMessageV3" :
				profiler = new cgp_GeneratedMessageV3_Timings(is_verbose);
				break;
			case "com.google.protobuf.Internal" :
				profiler = new cgp_Internal_Timings(is_verbose);
				break;
			case "com.google.protobuf.InvalidProtocolBufferException" :
				profiler = new cgp_InvalidProtocolBufferException_Timings(is_verbose);
				break;
			case "com.google.protobuf.LazyStringArrayList" :
				profiler = new cgp_LazyStringArrayList_Timings(is_verbose);
				break;
			case "com.google.protobuf.LazyStringList" :
				profiler = new cgp_LazyStringList_Timings(is_verbose);
				break;
			case "com.google.protobuf.Parser" :
				profiler = new cgp_Parser_Timings(is_verbose);
				break;
			case "com.google.protobuf.RepeatedFieldBuilderV3" :
				profiler = new cgp_RepeatedFieldBuilderV3_Timings(is_verbose);
				break;
			case "com.google.protobuf.SingleFieldBuilderV3" :
				profiler = new cgp_SingleFieldBuilderV3_Timings(is_verbose);
				break;
			case "com.google.protobuf.UnknownFieldSet$Builder" :
				profiler = new cgp_UnknownFieldSet$Builder_Timings(is_verbose);
				break;
			case "com.google.protobuf.UnknownFieldSet" :
				profiler = new cgp_UnknownFieldSet_Timings(is_verbose);
				break;
			case "io.netty.bootstrap.AbstractBootstrap" :
				profiler = new inb_AbstractBootstrap_Timings(is_verbose);
				break;
			case "io.netty.bootstrap.Bootstrap" :
				profiler = new inb_Bootstrap_Timings(is_verbose);
				break;
			case "io.netty.bootstrap.ServerBootstrap" :
				profiler = new inb_ServerBootstrap_Timings(is_verbose);
				break;
			case "io.netty.channel.Channel" :
				profiler = new inc_Channel_Timings(is_verbose);
				break;
			case "io.netty.channel.ChannelDuplexHandler" :
				profiler = new inc_ChannelDuplexHandler_Timings(is_verbose);
				break;
			case "io.netty.channel.ChannelFuture" :
				profiler = new inc_ChannelFuture_Timings(is_verbose);
				break;
			case "io.netty.channel.ChannelHandlerContext" :
				profiler = new inc_ChannelHandlerContext_Timings(is_verbose);
				break;
			case "io.netty.channel.ChannelInboundHandlerAdapter" :
				profiler = new inc_ChannelInboundHandlerAdapter_Timings(is_verbose);
				break;
			case "io.netty.channel.ChannelPipeline" :
				profiler = new inc_ChannelPipeline_Timings(is_verbose);
				break;
			case "io.netty.channel.nio.NioEventLoopGroup" :
				profiler = new incn_NioEventLoopGroup_Timings(is_verbose);
				break;
			case "io.netty.handler.codec.LengthFieldBasedFrameDecoder" :
				profiler = new inhc_LengthFieldBasedFrameDecoder_Timings(is_verbose);
				break;
			case "io.netty.handler.codec.LengthFieldPrepender" :
				profiler = new inhc_LengthFieldPrepender_Timings(is_verbose);
				break;
			case "io.netty.handler.codec.bytes.ByteArrayDecoder" :
				profiler = new inhcb_ByteArrayDecoder_Timings(is_verbose);
				break;
			case "io.netty.handler.codec.bytes.ByteArrayEncoder" :
				profiler = new inhcb_ByteArrayEncoder_Timings(is_verbose);
				break;
			case "io.netty.util.Attribute" :
				profiler = new inu_Attribute_Timings(is_verbose);
				break;
			case "io.netty.util.AttributeKey" :
				profiler = new inu_AttributeKey_Timings(is_verbose);
				break;
			case "io.netty.util.AttributeMap" :
				profiler = new inu_AttributeMap_Timings(is_verbose);
				break;
			case "io.netty.util.concurrent.EventExecutorGroup" :
				profiler = new inuc_EventExecutorGroup_Timings(is_verbose);
				break;
			case "io.netty.util.concurrent.Future" :
				profiler = new inuc_Future_Timings(is_verbose);
				break;
			case "io.netty.util.concurrent.Promise" :
				profiler = new inuc_Promise_Timings(is_verbose);
				break;
			case "jline.console.ConsoleReader" :
				profiler = new jc_ConsoleReader_Timings(is_verbose);
				break;
			case "jline.console.CursorBuffer" :
				profiler = new jc_CursorBuffer_Timings(is_verbose);
				break;
			case "jline.console.completer.AggregateCompleter" :
				profiler = new jcc_AggregateCompleter_Timings(is_verbose);
				break;
			case "jline.console.completer.ArgumentCompleter" :
				profiler = new jcc_ArgumentCompleter_Timings(is_verbose);
				break;
			case "jline.console.completer.FileNameCompleter" :
				profiler = new jcc_FileNameCompleter_Timings(is_verbose);
				break;
			case "jline.console.completer.StringsCompleter" :
				profiler = new jcc_StringsCompleter_Timings(is_verbose);
				break;
			case "org.slf4j.Logger" :
				profiler = new os_Logger_Timings(is_verbose);
				break;
			case "org.slf4j.LoggerFactory" :
				profiler = new os_LoggerFactory_Timings(is_verbose);
				break;
			case "java.lang.Object" :
				profiler = new jl_Object_Timings(is_verbose);
				break;
			case "org.springframework.boot.SpringApplication" :
				profiler = new osb_SpringApplication_Timings(is_verbose);
				break;
			case "org.springframework.web.servlet.config.annotation.PathMatchConfigurer" :
				profiler = new oswsca_PathMatchConfigurer_Timings(is_verbose);
				break;
			case "org.openzim.ZIMTypes.DirectoryEntry" :
				profiler = new ooZ_DirectoryEntry_Timings(is_verbose);
				break;
			case "org.openzim.ZIMTypes.ZIMReader" :
				profiler = new ooZ_ZIMReader_Timings(is_verbose);
				break;
			case "org.mapdb.BTreeMap" :
				profiler = new om_BTreeMap_Timings(is_verbose);
				break;
			case "org.mapdb.DB$HashMapMaker" :
				profiler = new om_DB$HashMapMaker_Timings(is_verbose);
				break;
			case "org.mapdb.DB$TreeMapMaker" :
				profiler = new om_DB$TreeMapMaker_Timings(is_verbose);
				break;
			case "org.mapdb.HTreeMap" :
				profiler = new om_HTreeMap_Timings(is_verbose);
				break;
			case "org.apache.commons.lang3.RandomStringUtils" :
				profiler = new oacl_RandomStringUtils_Timings(is_verbose);
				break;
			case "com.google.gson.Gson" :
				profiler = new cgg_Gson_Timings(is_verbose);
				break;
			case "com.google.gson.GsonBuilder" :
				profiler = new cgg_GsonBuilder_Timings(is_verbose);
				break;
			case "com.google.gson.reflect.TypeToken" :
				profiler = new cggr_TypeToken_Timings(is_verbose);
				break;
			case "org.apache.commons.math3.linear.Array2DRowRealMatrix" :
				profiler = new oacml_Array2DRowRealMatrix_Timings(is_verbose);
				break;
			case "org.apache.commons.math3.linear.ArrayRealVector" :
				profiler = new oacml_ArrayRealVector_Timings(is_verbose);
				break;
			case "org.apache.commons.math3.linear.RealMatrix" :
				profiler = new oacml_RealMatrix_Timings(is_verbose);
				break;
			case "org.apache.commons.math3.linear.RealVector" :
				profiler = new oacml_RealVector_Timings(is_verbose);
				break;
			case "org.jsoup.Jsoup" :
				profiler = new oj_Jsoup_Timings(is_verbose);
				break;
			case "org.jsoup.nodes.Document" :
				profiler = new ojn_Document_Timings(is_verbose);
				break;
			case "org.jsoup.nodes.Element" :
				profiler = new ojn_Element_Timings(is_verbose);
				break;
			case "org.jsoup.select.Elements" :
				profiler = new ojs_Elements_Timings(is_verbose);
				break;
			case "org.thymeleaf.spring4.SpringTemplateEngine" :
				profiler = new ots_SpringTemplateEngine_Timings(is_verbose);
				break;
			case "org.thymeleaf.templateresolver.FileTemplateResolver" :
				profiler = new ott_FileTemplateResolver_Timings(is_verbose);
				break;
			case "org.springframework.web.multipart.MultipartFile" :
				profiler = new oswm_MultipartFile_Timings(is_verbose);
				break;
			case "fi.iki.elonen.NanoHTTPD$IHTTPSession" :
				profiler = new fie_NanoHTTPD$IHTTPSession_Timings(is_verbose);
				break;
			case "fi.iki.elonen.NanoHTTPD$Method" :
				profiler = new fie_NanoHTTPD$Method_Timings(is_verbose);
				break;
			case "fi.iki.elonen.NanoHTTPD" :
				profiler = new fie_NanoHTTPD_Timings(is_verbose);
				break;
			case "org.slf4j.cal10n.LocLogger" :
				profiler = new osc_LocLogger_Timings(is_verbose);
				break;
			case "org.slf4j.cal10n.LocLoggerFactory" :
				profiler = new osc_LocLoggerFactory_Timings(is_verbose);
				break;
			case "fi.iki.elonen.NanoHTTPD$CookieHandler" :
				profiler = new fie_NanoHTTPD$CookieHandler_Timings(is_verbose);
				break;
			case "fi.iki.elonen.NanoHTTPD$Response" :
				profiler = new fie_NanoHTTPD$Response_Timings(is_verbose);
				break;
			case "com.fasterxml.jackson.databind.ObjectMapper" :
				profiler = new cfjd_ObjectMapper_Timings(is_verbose);
				break;
			case "org.apache.commons.lang3.StringEscapeUtils" :
				profiler = new oacl_StringEscapeUtils_Timings(is_verbose);
				break;
			case "org.apache.commons.lang3.StringUtils" :
				profiler = new oacl_StringUtils_Timings(is_verbose);
				break;
			case "org.apache.commons.lang3.text.WordUtils" :
				profiler = new oaclt_WordUtils_Timings(is_verbose);
				break;
			case "org.apache.commons.lang3.tuple.Pair" :
				profiler = new oaclt_Pair_Timings(is_verbose);
				break;
			case "org.apache.commons.codec.binary.Base64" :
				profiler = new oaccb_Base64_Timings(is_verbose);
				break;
			case "org.apache.http.NameValuePair" :
				profiler = new oah_NameValuePair_Timings(is_verbose);
				break;
			case "org.apache.http.client.utils.URLEncodedUtils" :
				profiler = new oahcu_URLEncodedUtils_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.CommandLine" :
				profiler = new oacc_CommandLine_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.CommandLineParser" :
				profiler = new oacc_CommandLineParser_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.DefaultParser" :
				profiler = new oacc_DefaultParser_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.HelpFormatter" :
				profiler = new oacc_HelpFormatter_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.Option$Builder" :
				profiler = new oacc_Option$Builder_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.Option" :
				profiler = new oacc_Option_Timings(is_verbose);
				break;
			case "org.apache.commons.cli.Options" :
				profiler = new oacc_Options_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.Filter$Chain" :
				profiler = new csnh_Filter$Chain_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.Headers" :
				profiler = new csnh_Headers_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.HttpContext" :
				profiler = new csnh_HttpContext_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.HttpExchange" :
				profiler = new csnh_HttpExchange_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.HttpServer" :
				profiler = new csnh_HttpServer_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.HttpsParameters" :
				profiler = new csnh_HttpsParameters_Timings(is_verbose);
				break;
			case "com.sun.net.httpserver.HttpsServer" :
				profiler = new csnh_HttpsServer_Timings(is_verbose);
				break;
			case "java.awt.Color" :
				profiler = new ja_Color_Timings(is_verbose);
				break;
			case "java.awt.Container" :
				profiler = new ja_Container_Timings(is_verbose);
				break;
			case "java.awt.Font" :
				profiler = new ja_Font_Timings(is_verbose);
				break;
			case "java.awt.FontMetrics" :
				profiler = new ja_FontMetrics_Timings(is_verbose);
				break;
			case "java.awt.Graphics" :
				profiler = new ja_Graphics_Timings(is_verbose);
				break;
			case "java.awt.Graphics2D" :
				profiler = new ja_Graphics2D_Timings(is_verbose);
				break;
			case "java.awt.color.ColorSpace" :
				profiler = new jac_ColorSpace_Timings(is_verbose);
				break;
			case "java.awt.geom.AffineTransform" :
				profiler = new jag_AffineTransform_Timings(is_verbose);
				break;
			case "java.awt.geom.Rectangle2D" :
				profiler = new jag_Rectangle2D_Timings(is_verbose);
				break;
			case "java.awt.image.BufferedImage" :
				profiler = new jai_BufferedImage_Timings(is_verbose);
				break;
			case "java.awt.image.BufferedImageOp" :
				profiler = new jai_BufferedImageOp_Timings(is_verbose);
				break;
			case "java.awt.image.DataBufferByte" :
				profiler = new jai_DataBufferByte_Timings(is_verbose);
				break;
			case "java.awt.image.WritableRaster" :
				profiler = new jai_WritableRaster_Timings(is_verbose);
				break;
			case "java.beans.XMLEncoder" :
				profiler = new jb_XMLEncoder_Timings(is_verbose);
				break;
			case "java.io.BufferedInputStream" :
				profiler = new ji_BufferedInputStream_Timings(is_verbose);
				break;
			case "java.io.BufferedOutputStream" :
				profiler = new ji_BufferedOutputStream_Timings(is_verbose);
				break;
			case "java.io.BufferedReader" :
				profiler = new ji_BufferedReader_Timings(is_verbose);
				break;
			case "java.io.ByteArrayInputStream" :
				profiler = new ji_ByteArrayInputStream_Timings(is_verbose);
				break;
			case "java.io.ByteArrayOutputStream" :
				profiler = new ji_ByteArrayOutputStream_Timings(is_verbose);
				break;
			case "java.io.DataInput" :
				profiler = new ji_DataInput_Timings(is_verbose);
				break;
			case "java.io.DataInputStream" :
				profiler = new ji_DataInputStream_Timings(is_verbose);
				break;
			case "java.io.DataOutput" :
				profiler = new ji_DataOutput_Timings(is_verbose);
				break;
			case "java.io.DataOutputStream" :
				profiler = new ji_DataOutputStream_Timings(is_verbose);
				break;
			case "java.io.File" :
				profiler = new ji_File_Timings(is_verbose);
				break;
			case "java.io.FileInputStream" :
				profiler = new ji_FileInputStream_Timings(is_verbose);
				break;
			case "java.io.FileOutputStream" :
				profiler = new ji_FileOutputStream_Timings(is_verbose);
				break;
			case "java.io.FileReader" :
				profiler = new ji_FileReader_Timings(is_verbose);
				break;
			case "java.io.FileWriter" :
				profiler = new ji_FileWriter_Timings(is_verbose);
				break;
			case "java.io.FilterInputStream" :
				profiler = new ji_FilterInputStream_Timings(is_verbose);
				break;
			case "java.io.FilterOutputStream" :
				profiler = new ji_FilterOutputStream_Timings(is_verbose);
				break;
			case "java.io.IOException" :
				profiler = new ji_IOException_Timings(is_verbose);
				break;
			case "java.io.InputStream" :
				profiler = new ji_InputStream_Timings(is_verbose);
				break;
			case "java.io.InputStreamReader" :
				profiler = new ji_InputStreamReader_Timings(is_verbose);
				break;
			case "java.io.ObjectInput" :
				profiler = new ji_ObjectInput_Timings(is_verbose);
				break;
			case "java.io.ObjectInputStream" :
				profiler = new ji_ObjectInputStream_Timings(is_verbose);
				break;
			case "java.io.ObjectOutput" :
				profiler = new ji_ObjectOutput_Timings(is_verbose);
				break;
			case "java.io.ObjectOutputStream" :
				profiler = new ji_ObjectOutputStream_Timings(is_verbose);
				break;
			case "java.io.OutputStream" :
				profiler = new ji_OutputStream_Timings(is_verbose);
				break;
			case "java.io.PrintStream" :
				profiler = new ji_PrintStream_Timings(is_verbose);
				break;
			case "java.io.PrintWriter" :
				profiler = new ji_PrintWriter_Timings(is_verbose);
				break;
            /*
			case "java.io.RandomAccessFile" :
				profiler = new ji_RandomAccessFile_Timings(is_verbose);
				break;
            */
			case "java.io.Reader" :
				profiler = new ji_Reader_Timings(is_verbose);
				break;
			case "java.io.StringReader" :
				profiler = new ji_StringReader_Timings(is_verbose);
				break;
			case "java.io.StringWriter" :
				profiler = new ji_StringWriter_Timings(is_verbose);
				break;
			case "java.io.Writer" :
				profiler = new ji_Writer_Timings(is_verbose);
				break;
			case "java.lang.Boolean" :
				profiler = new jl_Boolean_Timings(is_verbose);
				break;
			case "java.lang.Byte" :
				profiler = new jl_Byte_Timings(is_verbose);
				break;
			case "java.lang.CharSequence" :
				profiler = new jl_CharSequence_Timings(is_verbose);
				break;
			case "java.lang.Character" :
				profiler = new jl_Character_Timings(is_verbose);
				break;
			case "java.lang.Class" :
				profiler = new jl_Class_Timings(is_verbose);
				break;
			case "java.lang.ClassLoader" :
				profiler = new jl_ClassLoader_Timings(is_verbose);
				break;
			case "java.lang.Double" :
				profiler = new jl_Double_Timings(is_verbose);
				break;
			case "java.lang.Enum" :
				profiler = new jl_Enum_Timings(is_verbose);
				break;
			case "java.lang.Error" :
				profiler = new jl_Error_Timings(is_verbose);
				break;
			case "java.lang.Exception" :
				profiler = new jl_Exception_Timings(is_verbose);
				break;
			case "java.lang.Float" :
				profiler = new jl_Float_Timings(is_verbose);
				break;
			case "java.lang.IllegalArgumentException" :
				profiler = new jl_IllegalArgumentException_Timings(is_verbose);
				break;
			case "java.lang.IllegalStateException" :
				profiler = new jl_IllegalStateException_Timings(is_verbose);
				break;
			case "java.lang.IndexOutOfBoundsException" :
				profiler = new jl_IndexOutOfBoundsException_Timings(is_verbose);
				break;
			case "java.lang.Integer" :
				profiler = new jl_Integer_Timings(is_verbose);
				break;
			case "java.lang.InterruptedException" :
				profiler = new jl_InterruptedException_Timings(is_verbose);
				break;
			case "java.lang.Iterable" :
				profiler = new jl_Iterable_Timings(is_verbose);
				break;
			case "java.lang.Long" :
				profiler = new jl_Long_Timings(is_verbose);
				break;
			case "java.lang.Math" :
				profiler = new jl_Math_Timings(is_verbose);
				break;
			case "java.lang.NullPointerException" :
				profiler = new jl_NullPointerException_Timings(is_verbose);
				break;
			case "java.lang.Process" :
				profiler = new jl_Process_Timings(is_verbose);
				break;
			case "java.lang.ProcessBuilder" :
				profiler = new jl_ProcessBuilder_Timings(is_verbose);
				break;
			case "java.lang.Runtime" :
				profiler = new jl_Runtime_Timings(is_verbose);
				break;
			case "java.lang.RuntimeException" :
				profiler = new jl_RuntimeException_Timings(is_verbose);
				break;
			case "java.lang.SecurityManager" :
				profiler = new jl_SecurityManager_Timings(is_verbose);
				break;
			case "java.lang.Short" :
				profiler = new jl_Short_Timings(is_verbose);
				break;
			case "java.lang.String" :
				profiler = new jl_String_Timings(is_verbose);
				break;
			case "java.lang.StringBuffer" :
				profiler = new jl_StringBuffer_Timings(is_verbose);
				break;
			case "java.lang.StringBuilder" :
				profiler = new jl_StringBuilder_Timings(is_verbose);
				break;
			case "java.lang.System" :
				profiler = new jl_System_Timings(is_verbose);
				break;
			case "java.lang.Thread" :
				profiler = new jl_Thread_Timings(is_verbose);
				break;
			case "java.lang.ThreadGroup" :
				profiler = new jl_ThreadGroup_Timings(is_verbose);
				break;
			case "java.lang.Throwable" :
				profiler = new jl_Throwable_Timings(is_verbose);
				break;
			case "java.lang.UnsupportedOperationException" :
				profiler = new jl_UnsupportedOperationException_Timings(is_verbose);
				break;
			case "java.lang.invoke.LambdaMetafactory" :
				profiler = new jli_LambdaMetafactory_Timings(is_verbose);
				break;
			case "java.lang.ref.SoftReference" :
				profiler = new jlr_SoftReference_Timings(is_verbose);
				break;
			case "java.lang.reflect.Array" :
				profiler = new jlr_Array_Timings(is_verbose);
				break;
			case "java.lang.reflect.Constructor" :
				profiler = new jlr_Constructor_Timings(is_verbose);
				break;
			case "java.lang.reflect.Field" :
				profiler = new jlr_Field_Timings(is_verbose);
				break;
			case "java.lang.reflect.GenericArrayType" :
				profiler = new jlr_GenericArrayType_Timings(is_verbose);
				break;
			case "java.lang.reflect.Method" :
				profiler = new jlr_Method_Timings(is_verbose);
				break;
			case "java.lang.reflect.ParameterizedType" :
				profiler = new jlr_ParameterizedType_Timings(is_verbose);
				break;
			case "java.math.BigDecimal" :
				profiler = new jm_BigDecimal_Timings(is_verbose);
				break;
			case "java.math.BigInteger" :
				profiler = new jm_BigInteger_Timings(is_verbose);
				break;
			case "java.math.MathContext" :
				profiler = new jm_MathContext_Timings(is_verbose);
				break;
			case "java.net.InetAddress" :
				profiler = new jn_InetAddress_Timings(is_verbose);
				break;
			case "java.net.InetSocketAddress" :
				profiler = new jn_InetSocketAddress_Timings(is_verbose);
				break;
			case "java.net.ServerSocket" :
				profiler = new jn_ServerSocket_Timings(is_verbose);
				break;
			case "java.net.Socket" :
				profiler = new jn_Socket_Timings(is_verbose);
				break;
			case "java.net.URI" :
				profiler = new jn_URI_Timings(is_verbose);
				break;
			case "java.net.URL" :
				profiler = new jn_URL_Timings(is_verbose);
				break;
			case "java.net.URLConnection" :
				profiler = new jn_URLConnection_Timings(is_verbose);
				break;
			case "java.net.URLEncoder" :
				profiler = new jn_URLEncoder_Timings(is_verbose);
				break;
			case "java.nio.ByteBuffer" :
				profiler = new jn_ByteBuffer_Timings(is_verbose);
				break;
			case "java.nio.IntBuffer" :
				profiler = new jn_IntBuffer_Timings(is_verbose);
				break;
			case "java.nio.MappedByteBuffer" :
				profiler = new jn_MappedByteBuffer_Timings(is_verbose);
				break;
			case "java.nio.channels.ByteChannel" :
				profiler = new jnc_ByteChannel_Timings(is_verbose);
				break;
			case "java.nio.channels.Channels" :
				profiler = new jnc_Channels_Timings(is_verbose);
				break;
			case "java.nio.channels.FileChannel" :
				profiler = new jnc_FileChannel_Timings(is_verbose);
				break;
			case "java.nio.channels.ReadableByteChannel" :
				profiler = new jnc_ReadableByteChannel_Timings(is_verbose);
				break;
			case "java.nio.channels.SeekableByteChannel" :
				profiler = new jnc_SeekableByteChannel_Timings(is_verbose);
				break;
			case "java.nio.channels.SelectionKey" :
				profiler = new jnc_SelectionKey_Timings(is_verbose);
				break;
			case "java.nio.channels.Selector" :
				profiler = new jnc_Selector_Timings(is_verbose);
				break;
			case "java.nio.channels.ServerSocketChannel" :
				profiler = new jnc_ServerSocketChannel_Timings(is_verbose);
				break;
			case "java.nio.channels.SocketChannel" :
				profiler = new jnc_SocketChannel_Timings(is_verbose);
				break;
			case "java.nio.channels.WritableByteChannel" :
				profiler = new jnc_WritableByteChannel_Timings(is_verbose);
				break;
			case "java.nio.charset.Charset" :
				profiler = new jnc_Charset_Timings(is_verbose);
				break;
			case "java.nio.charset.CharsetDecoder" :
				profiler = new jnc_CharsetDecoder_Timings(is_verbose);
				break;
			case "java.nio.file.DirectoryStream" :
				profiler = new jnf_DirectoryStream_Timings(is_verbose);
				break;
			case "java.nio.file.Files" :
				profiler = new jnf_Files_Timings(is_verbose);
				break;
			case "java.nio.file.Path" :
				profiler = new jnf_Path_Timings(is_verbose);
				break;
			case "java.nio.file.Paths" :
				profiler = new jnf_Paths_Timings(is_verbose);
				break;
			case "java.security.AccessController" :
				profiler = new js_AccessController_Timings(is_verbose);
				break;
			case "java.security.AlgorithmParameters" :
				profiler = new js_AlgorithmParameters_Timings(is_verbose);
				break;
			case "java.security.KeyFactory" :
				profiler = new js_KeyFactory_Timings(is_verbose);
				break;
			case "java.security.KeyStore" :
				profiler = new js_KeyStore_Timings(is_verbose);
				break;
			case "java.security.MessageDigest" :
				profiler = new js_MessageDigest_Timings(is_verbose);
				break;
			case "java.security.NoSuchAlgorithmException" :
				profiler = new js_NoSuchAlgorithmException_Timings(is_verbose);
				break;
			case "java.security.SecureRandom" :
				profiler = new js_SecureRandom_Timings(is_verbose);
				break;
			case "java.security.Security" :
				profiler = new js_Security_Timings(is_verbose);
				break;
			case "java.text.DateFormat" :
				profiler = new jt_DateFormat_Timings(is_verbose);
				break;
			case "java.text.DecimalFormat" :
				profiler = new jt_DecimalFormat_Timings(is_verbose);
				break;
			case "java.text.SimpleDateFormat" :
				profiler = new jt_SimpleDateFormat_Timings(is_verbose);
				break;
			case "java.util.ArrayList" :
				profiler = new ju_ArrayList_Timings(is_verbose);
				break;
			case "java.util.Arrays" :
				profiler = new ju_Arrays_Timings(is_verbose);
				break;
			case "java.util.Base64$Decoder" :
				profiler = new ju_Base64$Decoder_Timings(is_verbose);
				break;
			case "java.util.Base64$Encoder" :
				profiler = new ju_Base64$Encoder_Timings(is_verbose);
				break;
			case "java.util.Base64" :
				profiler = new ju_Base64_Timings(is_verbose);
				break;
			case "java.util.Calendar" :
				profiler = new ju_Calendar_Timings(is_verbose);
				break;
			case "java.util.Collection" :
				profiler = new ju_Collection_Timings(is_verbose);
				break;
			case "java.util.Collections" :
				profiler = new ju_Collections_Timings(is_verbose);
				break;
			case "java.util.Comparator" :
				profiler = new ju_Comparator_Timings(is_verbose);
				break;
			case "java.util.Date" :
				profiler = new ju_Date_Timings(is_verbose);
				break;
			case "java.util.Deque" :
				profiler = new ju_Deque_Timings(is_verbose);
				break;
			case "java.util.EnumSet" :
				profiler = new ju_EnumSet_Timings(is_verbose);
				break;
			case "java.util.Enumeration" :
				profiler = new ju_Enumeration_Timings(is_verbose);
				break;
			case "java.util.HashMap" :
				profiler = new ju_HashMap_Timings(is_verbose);
				break;
			case "java.util.HashSet" :
				profiler = new ju_HashSet_Timings(is_verbose);
				break;
            case "java.util.Hashtable" :
                profiler = new ju_Hashtable_Timings(is_verbose);
                break;
			case "java.util.Iterator" :
				profiler = new ju_Iterator_Timings(is_verbose);
				break;
			case "java.util.LinkedHashMap" :
				profiler = new ju_LinkedHashMap_Timings(is_verbose);
				break;
			case "java.util.LinkedHashSet" :
				profiler = new ju_LinkedHashSet_Timings(is_verbose);
				break;
			case "java.util.LinkedList" :
				profiler = new ju_LinkedList_Timings(is_verbose);
				break;
			case "java.util.List" :
				profiler = new ju_List_Timings(is_verbose);
				break;
			case "java.util.ListIterator" :
				profiler = new ju_ListIterator_Timings(is_verbose);
				break;
			case "java.util.Locale$Builder" :
				profiler = new ju_Locale$Builder_Timings(is_verbose);
				break;
			case "java.util.Locale" :
				profiler = new ju_Locale_Timings(is_verbose);
				break;
			case "java.util.Map$Entry" :
				profiler = new ju_Map$Entry_Timings(is_verbose);
				break;
			case "java.util.Map" :
				profiler = new ju_Map_Timings(is_verbose);
				break;
			case "java.util.NoSuchElementException" :
				profiler = new ju_NoSuchElementException_Timings(is_verbose);
				break;
			case "java.util.Objects" :
				profiler = new ju_Objects_Timings(is_verbose);
				break;
			case "java.util.Properties" :
				profiler = new ju_Properties_Timings(is_verbose);
				break;
			case "java.util.Queue" :
				profiler = new ju_Queue_Timings(is_verbose);
				break;
			case "java.util.Random" :
				profiler = new ju_Random_Timings(is_verbose);
				break;
			case "java.util.Scanner" :
				profiler = new ju_Scanner_Timings(is_verbose);
				break;
			case "java.util.Set" :
				profiler = new ju_Set_Timings(is_verbose);
				break;
			case "java.util.StringTokenizer" :
				profiler = new ju_StringTokenizer_Timings(is_verbose);
				break;
			case "java.util.TreeMap" :
				profiler = new ju_TreeMap_Timings(is_verbose);
				break;
			case "java.util.TreeSet" :
				profiler = new ju_TreeSet_Timings(is_verbose);
				break;
			case "java.util.UUID" :
				profiler = new ju_UUID_Timings(is_verbose);
				break;
			case "java.util.Vector" :
				profiler = new ju_Vector_Timings(is_verbose);
				break;
			case "java.util.concurrent.BlockingQueue" :
				profiler = new juc_BlockingQueue_Timings(is_verbose);
				break;
			case "java.util.concurrent.ConcurrentLinkedQueue" :
				profiler = new juc_ConcurrentLinkedQueue_Timings(is_verbose);
				break;
			case "java.util.concurrent.CountDownLatch" :
				profiler = new juc_CountDownLatch_Timings(is_verbose);
				break;
			case "java.util.concurrent.Executor" :
				profiler = new juc_Executor_Timings(is_verbose);
				break;
			case "java.util.concurrent.ExecutorService" :
				profiler = new juc_ExecutorService_Timings(is_verbose);
				break;
			case "java.util.concurrent.Executors" :
				profiler = new juc_Executors_Timings(is_verbose);
				break;
			case "java.util.concurrent.Future" :
				profiler = new juc_Future_Timings(is_verbose);
				break;
			case "java.util.concurrent.LinkedBlockingQueue" :
				profiler = new juc_LinkedBlockingQueue_Timings(is_verbose);
				break;
			case "java.util.concurrent.ScheduledExecutorService" :
				profiler = new juc_ScheduledExecutorService_Timings(is_verbose);
				break;
			case "java.util.concurrent.ThreadLocalRandom" :
				profiler = new juc_ThreadLocalRandom_Timings(is_verbose);
				break;
			case "java.util.concurrent.ThreadPoolExecutor" :
				profiler = new juc_ThreadPoolExecutor_Timings(is_verbose);
				break;
			case "java.util.concurrent.TimeUnit" :
				profiler = new juc_TimeUnit_Timings(is_verbose);
				break;
			case "java.util.concurrent.atomic.AtomicInteger" :
				profiler = new juca_AtomicInteger_Timings(is_verbose);
				break;
			case "java.util.concurrent.atomic.AtomicReference" :
				profiler = new juca_AtomicReference_Timings(is_verbose);
				break;
			case "java.util.concurrent.locks.ReentrantLock" :
				profiler = new jucl_ReentrantLock_Timings(is_verbose);
				break;
			case "java.util.function.Predicate" :
				profiler = new juf_Predicate_Timings(is_verbose);
				break;
			case "java.util.logging.Logger" :
				profiler = new jul_Logger_Timings(is_verbose);
				break;
			case "java.util.regex.Matcher" :
				profiler = new jur_Matcher_Timings(is_verbose);
				break;
			case "java.util.regex.Pattern" :
				profiler = new jur_Pattern_Timings(is_verbose);
				break;
			case "java.util.stream.Collectors" :
				profiler = new jus_Collectors_Timings(is_verbose);
				break;
			case "java.util.stream.IntStream" :
				profiler = new jus_IntStream_Timings(is_verbose);
				break;
			case "java.util.stream.LongStream" :
				profiler = new jus_LongStream_Timings(is_verbose);
				break;
			case "java.util.stream.Stream" :
				profiler = new jus_Stream_Timings(is_verbose);
				break;
			case "java.util.zip.CRC32" :
				profiler = new juz_CRC32_Timings(is_verbose);
				break;
			case "java.util.zip.Deflater" :
				profiler = new juz_Deflater_Timings(is_verbose);
				break;
			case "javax.crypto.Cipher" :
				profiler = new jc_Cipher_Timings(is_verbose);
				break;
			case "javax.crypto.CipherInputStream" :
				profiler = new jc_CipherInputStream_Timings(is_verbose);
				break;
			case "javax.crypto.CipherOutputStream" :
				profiler = new jc_CipherOutputStream_Timings(is_verbose);
				break;
			case "javax.crypto.KeyGenerator" :
				profiler = new jc_KeyGenerator_Timings(is_verbose);
				break;
			case "javax.crypto.Mac" :
				profiler = new jc_Mac_Timings(is_verbose);
				break;
			case "javax.crypto.SecretKey" :
				profiler = new jc_SecretKey_Timings(is_verbose);
				break;
			case "javax.crypto.spec.IvParameterSpec" :
				profiler = new jcs_IvParameterSpec_Timings(is_verbose);
				break;
			case "javax.crypto.spec.SecretKeySpec" :
				profiler = new jcs_SecretKeySpec_Timings(is_verbose);
				break;
			case "javax.imageio.ImageIO" :
				profiler = new ji_ImageIO_Timings(is_verbose);
				break;
			case "javax.naming.SizeLimitExceededException" :
				profiler = new jn_SizeLimitExceededException_Timings(is_verbose);
				break;
			case "javax.net.ssl.KeyManagerFactory" :
				profiler = new jns_KeyManagerFactory_Timings(is_verbose);
				break;
			case "javax.net.ssl.SSLContext" :
				profiler = new jns_SSLContext_Timings(is_verbose);
				break;
			case "javax.net.ssl.SSLEngine" :
				profiler = new jns_SSLEngine_Timings(is_verbose);
				break;
			case "javax.net.ssl.SSLServerSocket" :
				profiler = new jns_SSLServerSocket_Timings(is_verbose);
				break;
			case "javax.net.ssl.SSLServerSocketFactory" :
				profiler = new jns_SSLServerSocketFactory_Timings(is_verbose);
				break;
			case "javax.net.ssl.SSLSocket" :
				profiler = new jns_SSLSocket_Timings(is_verbose);
				break;
			case "javax.net.ssl.SSLSocketFactory" :
				profiler = new jns_SSLSocketFactory_Timings(is_verbose);
				break;
			case "javax.servlet.http.HttpServletRequest" :
				profiler = new jsh_HttpServletRequest_Timings(is_verbose);
				break;
			case "javax.servlet.http.HttpServletResponse" :
				profiler = new jsh_HttpServletResponse_Timings(is_verbose);
				break;
			case "javax.servlet.http.HttpSession" :
				profiler = new jsh_HttpSession_Timings(is_verbose);
				break;
			case "javax.swing.JFrame" :
				profiler = new js_JFrame_Timings(is_verbose);
				break;
			case "javax.swing.JLabel" :
				profiler = new js_JLabel_Timings(is_verbose);
				break;
			case "javax.xml.parsers.SAXParser" :
				profiler = new jxp_SAXParser_Timings(is_verbose);
				break;
			case "javax.xml.parsers.SAXParserFactory" :
				profiler = new jxp_SAXParserFactory_Timings(is_verbose);
				break;
			case "org.xml.sax.Attributes" :
				profiler = new oxs_Attributes_Timings(is_verbose);
				break;
			case "sun.security.util.Debug" :
				profiler = new ssu_Debug_Timings(is_verbose);
				break;
			default:
                System.out.format("Could not find %s, falling through", classname);
                break;
        }

	return profiler;
    }
}
