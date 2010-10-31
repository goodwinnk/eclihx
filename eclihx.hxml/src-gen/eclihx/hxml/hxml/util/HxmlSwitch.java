/**
 * <copyright>
 * </copyright>
 *
 */
package eclihx.hxml.hxml.util;

import eclihx.hxml.hxml.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eclihx.hxml.hxml.HxmlPackage
 * @generated
 */
public class HxmlSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static HxmlPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HxmlSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = HxmlPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case HxmlPackage.MODEL:
      {
        Model model = (Model)theEObject;
        T result = caseModel(model);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.CLASSPATH:
      {
        Classpath classpath = (Classpath)theEObject;
        T result = caseClasspath(classpath);
        if (result == null) result = caseType(classpath);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.JAVASCRIPT:
      {
        Javascript javascript = (Javascript)theEObject;
        T result = caseJavascript(javascript);
        if (result == null) result = caseType(javascript);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.SWF:
      {
        Swf swf = (Swf)theEObject;
        T result = caseSwf(swf);
        if (result == null) result = caseType(swf);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.SWF9:
      {
        Swf9 swf9 = (Swf9)theEObject;
        T result = caseSwf9(swf9);
        if (result == null) result = caseType(swf9);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.NEKO:
      {
        Neko neko = (Neko)theEObject;
        T result = caseNeko(neko);
        if (result == null) result = caseType(neko);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.PHP:
      {
        Php php = (Php)theEObject;
        T result = casePhp(php);
        if (result == null) result = caseType(php);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.CPP:
      {
        Cpp cpp = (Cpp)theEObject;
        T result = caseCpp(cpp);
        if (result == null) result = caseType(cpp);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.XML:
      {
        Xml xml = (Xml)theEObject;
        T result = caseXml(xml);
        if (result == null) result = caseType(xml);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.MAIN:
      {
        Main main = (Main)theEObject;
        T result = caseMain(main);
        if (result == null) result = caseType(main);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.LIB:
      {
        Lib lib = (Lib)theEObject;
        T result = caseLib(lib);
        if (result == null) result = caseType(lib);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.FLAG:
      {
        Flag flag = (Flag)theEObject;
        T result = caseFlag(flag);
        if (result == null) result = caseType(flag);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.VERBOSE:
      {
        Verbose verbose = (Verbose)theEObject;
        T result = caseVerbose(verbose);
        if (result == null) result = caseType(verbose);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.DEBUG:
      {
        Debug debug = (Debug)theEObject;
        T result = caseDebug(debug);
        if (result == null) result = caseType(debug);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case HxmlPackage.TYPE:
      {
        Type type = (Type)theEObject;
        T result = caseType(type);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModel(Model object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Classpath</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Classpath</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClasspath(Classpath object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Javascript</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Javascript</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseJavascript(Javascript object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Swf</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Swf</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSwf(Swf object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Swf9</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Swf9</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSwf9(Swf9 object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Neko</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Neko</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNeko(Neko object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Php</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Php</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePhp(Php object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Cpp</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Cpp</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCpp(Cpp object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Xml</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Xml</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXml(Xml object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Main</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Main</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMain(Main object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Lib</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Lib</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLib(Lib object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Flag</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Flag</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFlag(Flag object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Verbose</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Verbose</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVerbose(Verbose object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Debug</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Debug</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDebug(Debug object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseType(Type object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object)
  {
    return null;
  }

} //HxmlSwitch
