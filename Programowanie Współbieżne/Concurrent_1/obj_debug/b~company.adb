pragma Ada_95;
pragma Source_File_Name (ada_main, Spec_File_Name => "b~company.ads");
pragma Source_File_Name (ada_main, Body_File_Name => "b~company.adb");

with System.Restrictions;
with Ada.Exceptions;

package body ada_main is
   pragma Warnings (Off);

   E124 : Short_Integer; pragma Import (Ada, E124, "system__os_lib_E");
   E015 : Short_Integer; pragma Import (Ada, E015, "system__soft_links_E");
   E025 : Short_Integer; pragma Import (Ada, E025, "system__exception_table_E");
   E119 : Short_Integer; pragma Import (Ada, E119, "ada__io_exceptions_E");
   E101 : Short_Integer; pragma Import (Ada, E101, "ada__tags_E");
   E110 : Short_Integer; pragma Import (Ada, E110, "ada__streams_E");
   E050 : Short_Integer; pragma Import (Ada, E050, "interfaces__c_E");
   E121 : Short_Integer; pragma Import (Ada, E121, "interfaces__c__strings_E");
   E031 : Short_Integer; pragma Import (Ada, E031, "system__exceptions_E");
   E118 : Short_Integer; pragma Import (Ada, E118, "system__finalization_root_E");
   E116 : Short_Integer; pragma Import (Ada, E116, "ada__finalization_E");
   E135 : Short_Integer; pragma Import (Ada, E135, "system__storage_pools_E");
   E129 : Short_Integer; pragma Import (Ada, E129, "system__finalization_masters_E");
   E141 : Short_Integer; pragma Import (Ada, E141, "system__storage_pools__subpools_E");
   E079 : Short_Integer; pragma Import (Ada, E079, "system__task_info_E");
   E008 : Short_Integer; pragma Import (Ada, E008, "ada__calendar_E");
   E006 : Short_Integer; pragma Import (Ada, E006, "ada__calendar__delays_E");
   E137 : Short_Integer; pragma Import (Ada, E137, "system__pool_global_E");
   E127 : Short_Integer; pragma Import (Ada, E127, "system__file_control_block_E");
   E114 : Short_Integer; pragma Import (Ada, E114, "system__file_io_E");
   E019 : Short_Integer; pragma Import (Ada, E019, "system__secondary_stack_E");
   E157 : Short_Integer; pragma Import (Ada, E157, "system__tasking__initialization_E");
   E147 : Short_Integer; pragma Import (Ada, E147, "system__tasking__protected_objects_E");
   E056 : Short_Integer; pragma Import (Ada, E056, "ada__real_time_E");
   E109 : Short_Integer; pragma Import (Ada, E109, "ada__text_io_E");
   E153 : Short_Integer; pragma Import (Ada, E153, "system__tasking__protected_objects__entries_E");
   E163 : Short_Integer; pragma Import (Ada, E163, "system__tasking__queuing_E");
   E171 : Short_Integer; pragma Import (Ada, E171, "system__tasking__stages_E");
   E145 : Short_Integer; pragma Import (Ada, E145, "semaphores_E");

   Local_Priority_Specific_Dispatching : constant String := "";
   Local_Interrupt_States : constant String := "";

   Is_Elaborated : Boolean := False;

   procedure finalize_library is
   begin
      E153 := E153 - 1;
      declare
         procedure F1;
         pragma Import (Ada, F1, "system__tasking__protected_objects__entries__finalize_spec");
      begin
         F1;
      end;
      E109 := E109 - 1;
      declare
         procedure F2;
         pragma Import (Ada, F2, "ada__text_io__finalize_spec");
      begin
         F2;
      end;
      E129 := E129 - 1;
      E141 := E141 - 1;
      declare
         procedure F3;
         pragma Import (Ada, F3, "system__file_io__finalize_body");
      begin
         E114 := E114 - 1;
         F3;
      end;
      declare
         procedure F4;
         pragma Import (Ada, F4, "system__file_control_block__finalize_spec");
      begin
         E127 := E127 - 1;
         F4;
      end;
      E137 := E137 - 1;
      declare
         procedure F5;
         pragma Import (Ada, F5, "system__pool_global__finalize_spec");
      begin
         F5;
      end;
      declare
         procedure F6;
         pragma Import (Ada, F6, "system__storage_pools__subpools__finalize_spec");
      begin
         F6;
      end;
      declare
         procedure F7;
         pragma Import (Ada, F7, "system__finalization_masters__finalize_spec");
      begin
         F7;
      end;
      declare
         procedure Reraise_Library_Exception_If_Any;
            pragma Import (Ada, Reraise_Library_Exception_If_Any, "__gnat_reraise_library_exception_if_any");
      begin
         Reraise_Library_Exception_If_Any;
      end;
   end finalize_library;

   procedure adafinal is
      procedure s_stalib_adafinal;
      pragma Import (C, s_stalib_adafinal, "system__standard_library__adafinal");
   begin
      if not Is_Elaborated then
         return;
      end if;
      Is_Elaborated := False;
      s_stalib_adafinal;
   end adafinal;

   type No_Param_Proc is access procedure;

   procedure adainit is
      Main_Priority : Integer;
      pragma Import (C, Main_Priority, "__gl_main_priority");
      Time_Slice_Value : Integer;
      pragma Import (C, Time_Slice_Value, "__gl_time_slice_val");
      WC_Encoding : Character;
      pragma Import (C, WC_Encoding, "__gl_wc_encoding");
      Locking_Policy : Character;
      pragma Import (C, Locking_Policy, "__gl_locking_policy");
      Queuing_Policy : Character;
      pragma Import (C, Queuing_Policy, "__gl_queuing_policy");
      Task_Dispatching_Policy : Character;
      pragma Import (C, Task_Dispatching_Policy, "__gl_task_dispatching_policy");
      Priority_Specific_Dispatching : System.Address;
      pragma Import (C, Priority_Specific_Dispatching, "__gl_priority_specific_dispatching");
      Num_Specific_Dispatching : Integer;
      pragma Import (C, Num_Specific_Dispatching, "__gl_num_specific_dispatching");
      Main_CPU : Integer;
      pragma Import (C, Main_CPU, "__gl_main_cpu");
      Interrupt_States : System.Address;
      pragma Import (C, Interrupt_States, "__gl_interrupt_states");
      Num_Interrupt_States : Integer;
      pragma Import (C, Num_Interrupt_States, "__gl_num_interrupt_states");
      Unreserve_All_Interrupts : Integer;
      pragma Import (C, Unreserve_All_Interrupts, "__gl_unreserve_all_interrupts");
      Detect_Blocking : Integer;
      pragma Import (C, Detect_Blocking, "__gl_detect_blocking");
      Default_Stack_Size : Integer;
      pragma Import (C, Default_Stack_Size, "__gl_default_stack_size");
      Leap_Seconds_Support : Integer;
      pragma Import (C, Leap_Seconds_Support, "__gl_leap_seconds_support");

      procedure Install_Handler;
      pragma Import (C, Install_Handler, "__gnat_install_handler");

      Handler_Installed : Integer;
      pragma Import (C, Handler_Installed, "__gnat_handler_installed");

      Finalize_Library_Objects : No_Param_Proc;
      pragma Import (C, Finalize_Library_Objects, "__gnat_finalize_library_objects");
   begin
      if Is_Elaborated then
         return;
      end if;
      Is_Elaborated := True;
      Main_Priority := -1;
      Time_Slice_Value := -1;
      WC_Encoding := 'b';
      Locking_Policy := ' ';
      Queuing_Policy := ' ';
      Task_Dispatching_Policy := ' ';
      System.Restrictions.Run_Time_Restrictions :=
        (Set =>
          (False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, True, False, False, False, False, 
           False, False, False, False, False, False),
         Value => (0, 0, 0, 0, 0, 0, 0),
         Violated =>
          (True, False, True, True, True, False, False, False, 
           False, True, True, True, True, False, False, True, 
           False, False, True, True, False, True, True, True, 
           True, True, True, True, False, True, False, True, 
           False, True, True, False, True, True, False, True, 
           False, True, False, False, True, False, False, True, 
           False, True, False, True, False, True, True, True, 
           False, False, True, False, False, True, False, True, 
           True, False, True, True, True, False, True, True, 
           False, False, True, False, True, False),
         Count => (1, 0, 0, 1, 0, 1, 0),
         Unknown => (False, False, False, True, False, True, False));
      Priority_Specific_Dispatching :=
        Local_Priority_Specific_Dispatching'Address;
      Num_Specific_Dispatching := 0;
      Main_CPU := -1;
      Interrupt_States := Local_Interrupt_States'Address;
      Num_Interrupt_States := 0;
      Unreserve_All_Interrupts := 0;
      Detect_Blocking := 0;
      Default_Stack_Size := -1;
      Leap_Seconds_Support := 0;

      if Handler_Installed = 0 then
         Install_Handler;
      end if;

      Finalize_Library_Objects := finalize_library'access;

      System.Soft_Links'Elab_Spec;
      System.Exception_Table'Elab_Body;
      E025 := E025 + 1;
      Ada.Io_Exceptions'Elab_Spec;
      E119 := E119 + 1;
      Ada.Tags'Elab_Spec;
      Ada.Streams'Elab_Spec;
      E110 := E110 + 1;
      Interfaces.C'Elab_Spec;
      Interfaces.C.Strings'Elab_Spec;
      System.Exceptions'Elab_Spec;
      E031 := E031 + 1;
      System.Finalization_Root'Elab_Spec;
      E118 := E118 + 1;
      Ada.Finalization'Elab_Spec;
      E116 := E116 + 1;
      System.Storage_Pools'Elab_Spec;
      E135 := E135 + 1;
      System.Finalization_Masters'Elab_Spec;
      System.Storage_Pools.Subpools'Elab_Spec;
      System.Task_Info'Elab_Spec;
      E079 := E079 + 1;
      Ada.Calendar'Elab_Spec;
      Ada.Calendar'Elab_Body;
      E008 := E008 + 1;
      Ada.Calendar.Delays'Elab_Body;
      E006 := E006 + 1;
      System.Pool_Global'Elab_Spec;
      E137 := E137 + 1;
      System.File_Control_Block'Elab_Spec;
      E127 := E127 + 1;
      System.File_Io'Elab_Body;
      E114 := E114 + 1;
      E141 := E141 + 1;
      System.Finalization_Masters'Elab_Body;
      E129 := E129 + 1;
      E121 := E121 + 1;
      E050 := E050 + 1;
      Ada.Tags'Elab_Body;
      E101 := E101 + 1;
      System.Soft_Links'Elab_Body;
      E015 := E015 + 1;
      System.Os_Lib'Elab_Body;
      E124 := E124 + 1;
      System.Secondary_Stack'Elab_Body;
      E019 := E019 + 1;
      System.Tasking.Initialization'Elab_Body;
      E157 := E157 + 1;
      System.Tasking.Protected_Objects'Elab_Body;
      E147 := E147 + 1;
      Ada.Real_Time'Elab_Spec;
      Ada.Real_Time'Elab_Body;
      E056 := E056 + 1;
      Ada.Text_Io'Elab_Spec;
      Ada.Text_Io'Elab_Body;
      E109 := E109 + 1;
      System.Tasking.Protected_Objects.Entries'Elab_Spec;
      E153 := E153 + 1;
      System.Tasking.Queuing'Elab_Body;
      E163 := E163 + 1;
      System.Tasking.Stages'Elab_Body;
      E171 := E171 + 1;
      E145 := E145 + 1;
   end adainit;

   procedure Ada_Main_Program;
   pragma Import (Ada, Ada_Main_Program, "_ada_company");

   function main
     (argc : Integer;
      argv : System.Address;
      envp : System.Address)
      return Integer
   is
      procedure Initialize (Addr : System.Address);
      pragma Import (C, Initialize, "__gnat_initialize");

      procedure Finalize;
      pragma Import (C, Finalize, "__gnat_finalize");
      SEH : aliased array (1 .. 2) of Integer;

      Ensure_Reference : aliased System.Address := Ada_Main_Program_Name'Address;
      pragma Volatile (Ensure_Reference);

   begin
      gnat_argc := argc;
      gnat_argv := argv;
      gnat_envp := envp;

      Initialize (SEH'Address);
      adainit;
      Ada_Main_Program;
      adafinal;
      Finalize;
      return (gnat_exit_status);
   end;

--  BEGIN Object file/option list
   --   /home/bartas/workspace/Concurrent_1/obj_debug/semaphores.o
   --   /home/bartas/workspace/Concurrent_1/obj_debug/company.o
   --   -L/home/bartas/workspace/Concurrent_1/obj_debug/
   --   -L/home/bartas/workspace/Concurrent_1/obj_debug/
   --   -L/usr/lib/gcc/i686-linux-gnu/4.8/adalib/
   --   -shared
   --   -lgnarl-4.8
   --   -lgnat-4.8
   --   -lpthread
--  END Object file/option list   

end ada_main;
