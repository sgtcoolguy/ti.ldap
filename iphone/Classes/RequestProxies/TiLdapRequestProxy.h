/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2012 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiProxy.h"
#import "TiLdapConnectionProxy.h"

@interface TiLdapRequestProxy : TiProxy {
@protected
    TiLdapConnectionProxy *_connection;
    KrollCallback   *_successCallback;
    KrollCallback   *_errorCallback;
    int _messageId;
    NSString    *_method;
    LDAPMessage *_ldapMessage;
}

-(id)initRequest:(NSString*)method connection:(TiProxy*)proxy args:(NSDictionary*)args;
-(BOOL)isConnectionValid;
-(BOOL)isConnectionBound;
-(void)handleSuccess:(id)result;
-(void)handleError:(int)errorCode errorMessage:(NSString*)errorMessage;

-(int)execute:(NSDictionary*)args async:(BOOL)async;
-(void)sendRequest:(NSDictionary*)args;
-(void)abandon:(id)args;

@end
