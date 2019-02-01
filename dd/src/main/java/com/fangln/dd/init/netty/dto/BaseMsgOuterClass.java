// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BaseMsg.proto

package com.fangln.dd.init.netty.dto;

public final class BaseMsgOuterClass {
  private BaseMsgOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface BaseMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.fangln.dd.init.netty.dto.BaseMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string token = 1;</code>
     */
    String getToken();
    /**
     * <code>string token = 1;</code>
     */
    com.google.protobuf.ByteString
        getTokenBytes();

    /**
     * <code>string info = 2;</code>
     */
    String getInfo();
    /**
     * <code>string info = 2;</code>
     */
    com.google.protobuf.ByteString
        getInfoBytes();

    /**
     * <code>string method = 3;</code>
     */
    String getMethod();
    /**
     * <code>string method = 3;</code>
     */
    com.google.protobuf.ByteString
        getMethodBytes();

    /**
     * <code>int32 os = 4;</code>
     */
    int getOs();
  }
  /**
   * Protobuf type {@code com.fangln.dd.init.netty.dto.BaseMsg}
   */
  public  static final class BaseMsg extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.fangln.dd.init.netty.dto.BaseMsg)
      BaseMsgOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use BaseMsg.newBuilder() to construct.
    private BaseMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private BaseMsg() {
      token_ = "";
      info_ = "";
      method_ = "";
      os_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private BaseMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              String s = input.readStringRequireUtf8();

              token_ = s;
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              info_ = s;
              break;
            }
            case 26: {
              String s = input.readStringRequireUtf8();

              method_ = s;
              break;
            }
            case 32: {

              os_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return BaseMsgOuterClass.internal_static_com_fangln_dd_init_netty_dto_BaseMsg_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return BaseMsgOuterClass.internal_static_com_fangln_dd_init_netty_dto_BaseMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              BaseMsg.class, Builder.class);
    }

    public static final int TOKEN_FIELD_NUMBER = 1;
    private volatile Object token_;
    /**
     * <code>string token = 1;</code>
     */
    public String getToken() {
      Object ref = token_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        token_ = s;
        return s;
      }
    }
    /**
     * <code>string token = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTokenBytes() {
      Object ref = token_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        token_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int INFO_FIELD_NUMBER = 2;
    private volatile Object info_;
    /**
     * <code>string info = 2;</code>
     */
    public String getInfo() {
      Object ref = info_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        info_ = s;
        return s;
      }
    }
    /**
     * <code>string info = 2;</code>
     */
    public com.google.protobuf.ByteString
        getInfoBytes() {
      Object ref = info_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        info_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int METHOD_FIELD_NUMBER = 3;
    private volatile Object method_;
    /**
     * <code>string method = 3;</code>
     */
    public String getMethod() {
      Object ref = method_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        method_ = s;
        return s;
      }
    }
    /**
     * <code>string method = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMethodBytes() {
      Object ref = method_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        method_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int OS_FIELD_NUMBER = 4;
    private int os_;
    /**
     * <code>int32 os = 4;</code>
     */
    public int getOs() {
      return os_;
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getTokenBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, token_);
      }
      if (!getInfoBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, info_);
      }
      if (!getMethodBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, method_);
      }
      if (os_ != 0) {
        output.writeInt32(4, os_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getTokenBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, token_);
      }
      if (!getInfoBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, info_);
      }
      if (!getMethodBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, method_);
      }
      if (os_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, os_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof BaseMsg)) {
        return super.equals(obj);
      }
      BaseMsg other = (BaseMsg) obj;

      boolean result = true;
      result = result && getToken()
          .equals(other.getToken());
      result = result && getInfo()
          .equals(other.getInfo());
      result = result && getMethod()
          .equals(other.getMethod());
      result = result && (getOs()
          == other.getOs());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TOKEN_FIELD_NUMBER;
      hash = (53 * hash) + getToken().hashCode();
      hash = (37 * hash) + INFO_FIELD_NUMBER;
      hash = (53 * hash) + getInfo().hashCode();
      hash = (37 * hash) + METHOD_FIELD_NUMBER;
      hash = (53 * hash) + getMethod().hashCode();
      hash = (37 * hash) + OS_FIELD_NUMBER;
      hash = (53 * hash) + getOs();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static BaseMsg parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static BaseMsg parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static BaseMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static BaseMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static BaseMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static BaseMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static BaseMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static BaseMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static BaseMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static BaseMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static BaseMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static BaseMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(BaseMsg prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.fangln.dd.init.netty.dto.BaseMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.fangln.dd.init.netty.dto.BaseMsg)
        BaseMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return BaseMsgOuterClass.internal_static_com_fangln_dd_init_netty_dto_BaseMsg_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return BaseMsgOuterClass.internal_static_com_fangln_dd_init_netty_dto_BaseMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                BaseMsg.class, Builder.class);
      }

      // Construct using com.fangln.dd.init.netty.dto.BaseMsgOuterClass.BaseMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        token_ = "";

        info_ = "";

        method_ = "";

        os_ = 0;

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return BaseMsgOuterClass.internal_static_com_fangln_dd_init_netty_dto_BaseMsg_descriptor;
      }

      @Override
      public BaseMsg getDefaultInstanceForType() {
        return BaseMsg.getDefaultInstance();
      }

      @Override
      public BaseMsg build() {
        BaseMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public BaseMsg buildPartial() {
        BaseMsg result = new BaseMsg(this);
        result.token_ = token_;
        result.info_ = info_;
        result.method_ = method_;
        result.os_ = os_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof BaseMsg) {
          return mergeFrom((BaseMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(BaseMsg other) {
        if (other == BaseMsg.getDefaultInstance()) return this;
        if (!other.getToken().isEmpty()) {
          token_ = other.token_;
          onChanged();
        }
        if (!other.getInfo().isEmpty()) {
          info_ = other.info_;
          onChanged();
        }
        if (!other.getMethod().isEmpty()) {
          method_ = other.method_;
          onChanged();
        }
        if (other.getOs() != 0) {
          setOs(other.getOs());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        BaseMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (BaseMsg) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object token_ = "";
      /**
       * <code>string token = 1;</code>
       */
      public String getToken() {
        Object ref = token_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          token_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string token = 1;</code>
       */
      public com.google.protobuf.ByteString
          getTokenBytes() {
        Object ref = token_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          token_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string token = 1;</code>
       */
      public Builder setToken(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        token_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string token = 1;</code>
       */
      public Builder clearToken() {
        
        token_ = getDefaultInstance().getToken();
        onChanged();
        return this;
      }
      /**
       * <code>string token = 1;</code>
       */
      public Builder setTokenBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        token_ = value;
        onChanged();
        return this;
      }

      private Object info_ = "";
      /**
       * <code>string info = 2;</code>
       */
      public String getInfo() {
        Object ref = info_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          info_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string info = 2;</code>
       */
      public com.google.protobuf.ByteString
          getInfoBytes() {
        Object ref = info_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          info_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string info = 2;</code>
       */
      public Builder setInfo(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        info_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string info = 2;</code>
       */
      public Builder clearInfo() {
        
        info_ = getDefaultInstance().getInfo();
        onChanged();
        return this;
      }
      /**
       * <code>string info = 2;</code>
       */
      public Builder setInfoBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        info_ = value;
        onChanged();
        return this;
      }

      private Object method_ = "";
      /**
       * <code>string method = 3;</code>
       */
      public String getMethod() {
        Object ref = method_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          method_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string method = 3;</code>
       */
      public com.google.protobuf.ByteString
          getMethodBytes() {
        Object ref = method_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          method_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string method = 3;</code>
       */
      public Builder setMethod(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        method_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string method = 3;</code>
       */
      public Builder clearMethod() {
        
        method_ = getDefaultInstance().getMethod();
        onChanged();
        return this;
      }
      /**
       * <code>string method = 3;</code>
       */
      public Builder setMethodBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        method_ = value;
        onChanged();
        return this;
      }

      private int os_ ;
      /**
       * <code>int32 os = 4;</code>
       */
      public int getOs() {
        return os_;
      }
      /**
       * <code>int32 os = 4;</code>
       */
      public Builder setOs(int value) {
        
        os_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 os = 4;</code>
       */
      public Builder clearOs() {
        
        os_ = 0;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.fangln.dd.init.netty.dto.BaseMsg)
    }

    // @@protoc_insertion_point(class_scope:com.fangln.dd.init.netty.dto.BaseMsg)
    private static final BaseMsg DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new BaseMsg();
    }

    public static BaseMsg getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<BaseMsg>
        PARSER = new com.google.protobuf.AbstractParser<BaseMsg>() {
      @Override
      public BaseMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BaseMsg(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<BaseMsg> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<BaseMsg> getParserForType() {
      return PARSER;
    }

    @Override
    public BaseMsg getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_fangln_dd_init_netty_dto_BaseMsg_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_fangln_dd_init_netty_dto_BaseMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rBaseMsg.proto\022\034com.fangln.dd.init.nett" +
      "y.dto\"B\n\007BaseMsg\022\r\n\005token\030\001 \001(\t\022\014\n\004info\030" +
      "\002 \001(\t\022\016\n\006method\030\003 \001(\t\022\n\n\002os\030\004 \001(\005b\006proto" +
      "3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_fangln_dd_init_netty_dto_BaseMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_fangln_dd_init_netty_dto_BaseMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_fangln_dd_init_netty_dto_BaseMsg_descriptor,
        new String[] { "Token", "Info", "Method", "Os", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
