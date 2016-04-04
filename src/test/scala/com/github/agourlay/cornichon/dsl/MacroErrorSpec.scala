package com.github.agourlay.cornichon.dsl

import org.scalatest.{ WordSpec, Matchers }

class MacroErrorSpec extends WordSpec with Matchers {

  "Macro" should {
    "compile valid feature definitions" in {
      """
        import com.github.agourlay.cornichon.CornichonFeature
        import com.github.agourlay.cornichon.steps.regular.EffectStep

        class Foo extends CornichonFeature {
          val feature =
            Feature("foo") {
              Scenario("aaa") {
                EffectStep("just testing", identity)

                Repeat(10) {
                  EffectStep("just testing repeat", identity)
                }
              }
            }
        }
      """ should compile
    }

    "not compile if feature definition contains invalid expressions" in {
      """
        import com.github.agourlay.cornichon.CornichonFeature
        import com.github.agourlay.cornichon.steps.regular.EffectStep

        class Foo extends CornichonFeature {
          val feature =
            Feature("foo") {
              Scenario("aaa") {
                EffectStep("just testing", identity)

                val oops = "Hello World!"

                Repeat(10) {
                  EffectStep("just testing repeat", identity)
                }
              }
            }
        }
      """ shouldNot typeCheck
    }
  }

}
