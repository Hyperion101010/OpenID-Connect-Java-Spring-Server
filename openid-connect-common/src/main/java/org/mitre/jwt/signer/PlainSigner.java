/*******************************************************************************
 * Copyright 2013 The MITRE Corporation 
 *   and the MIT Kerberos and Internet Trust Consortium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.mitre.jwt.signer;

import java.util.Set;

import com.google.common.collect.Sets;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.PlainHeader;
import com.nimbusds.jose.ReadOnlyJWSHeader;
import com.nimbusds.jose.Requirement;
import com.nimbusds.jose.util.Base64URL;

/**
 * Signer to support "alg:none" JWS signing option (no signature).
 * 
 * FIXME: The JWSSigner interface was never intended to be used with plain JWTs.
 * Use of the signer/verifier pattern alongside the other JWSSigner/Verifiers will require refactoring.
 * 
 * @author wkim
 *
 */
public final class PlainSigner implements JWSSigner {


	@Override
	public Set<JWSAlgorithm> supportedAlgorithms() {
		return Sets.newHashSet(new JWSAlgorithm("none", Requirement.REQUIRED));
	}

	@Override
	public Base64URL sign(ReadOnlyJWSHeader header, byte[] signingInput) throws JOSEException {
		
		if (header instanceof PlainHeader) { // XXX impossible due to interface method signature
			
			return new Base64URL("");
			
		} else { 
			
			throw new JOSEException("Invalid header. This signer is for use with Plain JWTs only.");
			
		}
	}
	
	/**
	 * Utility method to return an empty signature.
	 * 
	 * @return
	 */
	public static Base64URL sign() {
		
		return new Base64URL("");
	}

}
