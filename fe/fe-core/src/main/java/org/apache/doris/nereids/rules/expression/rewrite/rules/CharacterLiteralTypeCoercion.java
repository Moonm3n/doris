// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.rules.expression.rewrite.rules;

import org.apache.doris.nereids.annotation.DependsRules;
import org.apache.doris.nereids.jobs.batch.CheckLegalityBeforeTypeCoercion;
import org.apache.doris.nereids.rules.expression.rewrite.AbstractExpressionRewriteRule;
import org.apache.doris.nereids.rules.expression.rewrite.ExpressionRewriteContext;
import org.apache.doris.nereids.trees.expressions.BinaryArithmetic;
import org.apache.doris.nereids.trees.expressions.Expression;
import org.apache.doris.nereids.util.TypeCoercionUtils;

/**
 * coercion character literal to another side
 */
@Deprecated
@DependsRules(CheckLegalityBeforeTypeCoercion.class)
public class CharacterLiteralTypeCoercion extends AbstractExpressionRewriteRule {

    public static final CharacterLiteralTypeCoercion INSTANCE = new CharacterLiteralTypeCoercion();

    @Override
    public Expression visitBinaryArithmetic(BinaryArithmetic binaryOperator, ExpressionRewriteContext context) {
        Expression left = rewrite(binaryOperator.left(), context);
        Expression right = rewrite(binaryOperator.right(), context);
        return TypeCoercionUtils.processCharacterLiteralInBinaryOperator(binaryOperator, left, right);
    }
}
